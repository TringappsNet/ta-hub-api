package tahub.sdapitahub.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.DTO.TAUserDTO;
import tahub.sdapitahub.Entity.TAUser;
import tahub.sdapitahub.Repository.TAUserRepository;
import tahub.sdapitahub.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private TAUserRepository taUserRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<TAUser> register(@RequestBody TAUserDTO userDTO) {
        TAUser registeredUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody TAUserDTO userDTO) {
        TAUser user = userService.findUserByEmail(userDTO.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");

        }

        boolean passwordMatches = userService.checkPasswordMatch(userDTO.getPassword(), user.getPassword());
        if (!passwordMatches) {
            throw new BadCredentialsException("Invalid password");
        }

        return ResponseEntity.status(200).body(user.getEmail());
    }

    @PostMapping("/reset-new-password")
    public ResponseEntity<Object> resetPassword(@RequestBody TAUserDTO userDTO) {
        TAUser user = userService.findUserByEmail(userDTO.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        boolean oldPasswordMatches = userService.checkPasswordMatch(userDTO.getOldPassword(), user.getPassword());
        if (!oldPasswordMatches) {
            throw new BadCredentialsException("Invalid old password");
        }

        user.setPassword(userService.encodePassword(userDTO.getNewPassword()));
        userService.saveUser(user);

        return ResponseEntity.status(200).body("Password reset successfully");
    }



    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestBody TAUserDTO userDTO) {
        String email = userDTO.getEmail();
        userService.forgetPassword(email);
        return ResponseEntity.status(200).body("Password reset link sent to email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestParam("token") String token,
                                                @RequestBody TAUserDTO userDTO) {
        String newPassword = userDTO.getPassword();
        userService.resetPassword(token, newPassword);
        return ResponseEntity.status(200).body("Password reset successfully");
    }
}



