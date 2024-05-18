package tahub.sdapitahub.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.dto.TaUserDTO;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.repository.TaUserRepository;
import tahub.sdapitahub.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TaUserRepository taUserRepository;
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TaUser> register(@RequestBody TaUser user) {
        TaUser registeredUser = authService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody TaUserDTO userDTO, HttpServletRequest request) {
        TaUser user = authService.findUserByEmail(userDTO.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        boolean passwordMatches = authService.checkPasswordMatch(userDTO.getPassword(), user.getPassword());
        if (!passwordMatches) {
            throw new BadCredentialsException("Invalid password");
        }

        // Create session
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedInUser", user);
        session.setMaxInactiveInterval(24 * 60 * 60);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("user", user);
        responseData.put("sessionId", session.getId());
        responseData.put("sessionCreationTime", session.getCreationTime());
        responseData.put("sessionLastAccessedTime", session.getLastAccessedTime());
        responseData.put("sessionMaxInactiveInterval", session.getMaxInactiveInterval());

        return ResponseEntity.status(200).body(responseData);
    }


    @PostMapping("/reset-new-password")
    public ResponseEntity<Object> resetPassword(@RequestBody TaUserDTO userDTO) {
        TaUser user = authService.findUserByEmail(userDTO.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        boolean oldPasswordMatches = authService.checkPasswordMatch(userDTO.getOldPassword(), user.getPassword());
        if (!oldPasswordMatches) {
            throw new BadCredentialsException("Invalid old password");
        }

        user.setPassword(authService.encodePassword(userDTO.getNewPassword()));
        authService.saveUser(user);

        return ResponseEntity.status(200).body("Password reset successfully");
    }



    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestBody TaUserDTO userDTO) {
        String email = userDTO.getEmail();
        authService.forgetPassword(email);
        return ResponseEntity.status(200).body("Password reset link sent to email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword) {
        TaUser user = authService.findUserByResetToken(token);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!authService.isTokenValid(user, token)) {
            throw new BadCredentialsException("Invalid token");
        }

        authService.resetPassword(user, newPassword);

        return ResponseEntity.status(200).body("Password reset successfully");
    }


    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("accessToken");
            session.removeAttribute("refreshToken");

            TaUser user = (TaUser) session.getAttribute("loggedInUser");
            if (user != null) {
                // Clear tokens from database
                user.setgAccessToken(null);
                user.setgRefreshToken(null);
                taUserRepository.update(user);
                session.removeAttribute("loggedInUser");
            }
            // Invalidate session
            session.invalidate();
        }

        return ResponseEntity.status(200).body("Logged out successfully");
    }

}



