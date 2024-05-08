package tahub.sdapitahub.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.DTO.TaUserDTO;
import tahub.sdapitahub.Entity.TaUser;
import tahub.sdapitahub.Repository.TaUserRepository;
import tahub.sdapitahub.Service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TaUserRepository taUserRepository;
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TaUser> register(@RequestBody TaUserDTO userDTO) {
        TaUser registeredUser = authService.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody TaUserDTO userDTO) {
        TaUser user = authService.findUserByEmail(userDTO.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");

        }

        boolean passwordMatches = authService.checkPasswordMatch(userDTO.getPassword(), user.getPassword());
        if (!passwordMatches) {
            throw new BadCredentialsException("Invalid password");
        }

        return ResponseEntity.status(200).body(user.getEmail());
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
}



