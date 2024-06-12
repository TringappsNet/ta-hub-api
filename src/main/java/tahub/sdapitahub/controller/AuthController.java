package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.Utils.TokenUtil;
import tahub.sdapitahub.dto.TaUserDTO;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.repository.TaUserRepository;
import tahub.sdapitahub.service.AuthService;
import java.util.Optional;
import tahub.sdapitahub.constants.AuthMessages;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Authentication", description = "Operations related to Authentication")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TaUserRepository taUserRepository;
    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody TaUser user, @RequestParam String inviteToken) {
        Optional<TaUser> invitedUserOptional = authService.findUserByInviteToken(inviteToken);
        if (!invitedUserOptional.isPresent()) {
            return ResponseEntity.badRequest().body(AuthMessages.INVALID_INVITE_TOKEN.getMessage());
        }
        TaUser invitedUser = invitedUserOptional.get();
        invitedUser.setUsername(user.getUsername());
        invitedUser.setPhone(user.getPhone());
        invitedUser.setPassword(user.getPassword());
        invitedUser.setInviteToken(null);

        TaUser registeredUser = authService.registerUser(invitedUser);

        return ResponseEntity.ok(registeredUser);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody TaUserDTO userDTO, HttpServletRequest request) {

            TaUser user = authService.findUserByEmail(userDTO.getEmail());
            if (user == null || !authService.checkPasswordMatch(userDTO.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"" + AuthMessages.INVALID_CREDENTIALS.getMessage() + "\"}");
            }


            // Create session
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedInUser", user);
            session.setMaxInactiveInterval(24 * 60 * 60);

            // Update user's current session ID
            user.setCurrentSessionId(session.getId());
            user.setLastLoginTime(LocalDateTime.now());
            taUserRepository.update(user);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("user", user);
            responseData.put("sessionId", session.getId());
            responseData.put("sessionCreationTime", session.getCreationTime());
            responseData.put("sessionLastAccessedTime", session.getLastAccessedTime());
            responseData.put("sessionMaxInactiveInterval", session.getMaxInactiveInterval());
            responseData.put("message", "Login success");


            return ResponseEntity.status(200).body(responseData);
        }



    @PostMapping("/reset-new-password")

    public ResponseEntity<Object> resetPassword(
            @RequestBody TaUserDTO userDTO,
            @RequestHeader("Session-Id") String sessionId,
            @RequestHeader("Email") String email) {
        TaUser user = authService.findUserByEmail(userDTO.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        boolean oldPasswordMatches = authService.checkPasswordMatch(userDTO.getOldPassword(), user.getPassword());
        if (!oldPasswordMatches) {
            throw new BadCredentialsException("Invalid old password");
        }

        user.setPassword(authService.encodePassword(userDTO.getNewPassword()));
        authService.updateUser(user);

        return ResponseEntity.status(200).body(AuthMessages.RESET_PASSWORD.getMessage());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestBody TaUserDTO userDTO) {
        String email = userDTO.getEmail();
        authService.forgetPassword(email);
        return ResponseEntity.status(200).body(AuthMessages.FORGOT_PASSWORD.getMessage());
    }


    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword) {
        TaUser user = authService.findUserByResetToken(token);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!TokenUtil.isResetTokenValid(user, token)) {
            throw new BadCredentialsException("Invalid token");
        }

        authService.resetPassword(user, newPassword);

        return ResponseEntity.status(200).body(AuthMessages.RESET_PASS_SUCCESS.getMessage());
    }


    @PostMapping("/send-invite")
    public ResponseEntity<Object> sendInvite(@RequestBody TaUserDTO userDTO) {
        String email = userDTO.getEmail();
        Long roleId =  userDTO.getRoleId();

        if (email == null || roleId <= 0) {
            return ResponseEntity.badRequest().body("Invalid request body");
        }
        try {
            TaUser user = authService.sendInvitation(email, roleId);
            return ResponseEntity.ok("Invitation sent to " + email);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send invitation: " + e.getMessage());
        }
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
                user.setgTokenExpiresIn(null);
                user.setCurrentSessionId(null);
                taUserRepository.update(user);
                session.removeAttribute("loggedInUser");
            }
            // Invalidate session
            session.invalidate();
        }

        return ResponseEntity.status(200).body(AuthMessages.LOGOUT.getMessage());
    }

}



