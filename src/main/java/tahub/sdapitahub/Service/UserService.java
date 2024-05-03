package tahub.sdapitahub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.DTO.TAUserDTO;
import tahub.sdapitahub.Entity.TAUser;
import tahub.sdapitahub.Repository.TAUserRepository;
import java.time.LocalDate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private TAUserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private SecretKey secretKey = new SecretKeySpec(SecureRandom.getSeed(16), "AES");
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 10;

    public TAUser registerUser(TAUserDTO TAUserDTO) {
        String hashedPassword = passwordEncoder.encode(TAUserDTO.getPassword());

        TAUser user = new TAUser();
        user.setUsername(TAUserDTO.getUsername());
        user.setEmail(TAUserDTO.getEmail());
        user.setPhone(TAUserDTO.getPhone());
        user.setPassword(hashedPassword);
        user.setIsActive(TAUserDTO.isActive());
        user.setRegistrationDate(TAUserDTO.getRegistrationDate());
        user.setRegistrationDate(LocalDate.now());

        return userRepository.save(user);
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public TAUser findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public TAUser saveUser(TAUser user) {
        return userRepository.save(user);
    }


    public void forgetPassword(String email) {
        TAUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String token = generateRandomString();
        String encryptedToken = encryptToken(token);
        user.setResetToken(encryptedToken);
        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, please visit the following link: " +
                "http://localhost:3000/forget-password?token=" + encryptedToken);
        mailSender.send(message);
    }

    private String encryptToken(String token) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(token.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting token", e);
        }
    }
    public void resetPassword(String token, String newPassword) {
        String decryptedToken = decryptToken(token);
        if (decryptedToken == null) {
            throw new BadCredentialsException("Invalid reset password token");
        }

        TAUser user = userRepository.findByResetToken(decryptedToken);
        if (user == null) {
            throw new BadCredentialsException("Invalid reset password token");
        }

        user.setPassword(encodePassword(newPassword));
        user.setResetToken(null);
        userRepository.save(user);
    }

    private String decryptToken(String token) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(token);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting token", e);
        }
    }
    private String generateRandomString() {
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

}