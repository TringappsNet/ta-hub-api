package tahub.sdapitahub.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.repository.TaUserRepository;
import tahub.sdapitahub.Utils.MailUtil;
import tahub.sdapitahub.Utils.TokenUtil;

import java.time.LocalDateTime;
import java.util.Random;
import java.security.SecureRandom;

@Service
public class AuthService {

    @Autowired
    private TaUserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public TaUser registerUser(TaUser taUser) {
        String hashedPassword = passwordEncoder.encode(taUser.getPassword());
        taUser.setPassword(hashedPassword);
        taUser.setIsActive(true);
        taUser.setCreatedAt(LocalDateTime.now());
        taUser.setLastUpdated(LocalDateTime.now());
        return userRepository.save(taUser);
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public TaUser findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public TaUser saveUser(TaUser user) {
        return userRepository.save(user);
    }

    public void forgetPassword(String email) {
        TaUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String token = TokenUtil.generateRandomString();
        String encryptedToken = TokenUtil.encryptToken(token);
        user.setResetToken(encryptedToken);
        userRepository.update(user);

        String subject = "Password Reset Request";
        String text = "To reset your password, please visit the following link: " +
                "http://localhost:3000/forget-password?token=" + encryptedToken;
        MailUtil.sendMail(user.getEmail(), subject, text);
    }


    public boolean isTokenValid(TaUser user, String token) {
        String decryptedToken = TokenUtil.decryptToken(user.getResetToken());
        return decryptedToken.equals(token);
    }

    public TaUser resetPassword(TaUser user, String newPassword) {
        user.setPassword(encodePassword(newPassword));
        user.setResetToken(null);
        return saveUser(user);
    }

    public TaUser findUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }
}
