package tahub.sdapitahub.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.DTO.TaUserDTO;
import tahub.sdapitahub.Entity.TaUser;
import tahub.sdapitahub.Repository.TaUserRepository;
import java.time.LocalDateTime;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private TaUserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final String SECRET_KEY_ALGORITHM = "AES";
    private static final int KEY_LENGTH_IN_BITS = 128;
    private static final int KEY_LENGTH_IN_BYTES = KEY_LENGTH_IN_BITS / 8;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = new byte[KEY_LENGTH_IN_BYTES];
        SecureRandom random = new SecureRandom();
        random.nextBytes(keyBytes);
        secretKey = new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM);
    }    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 10;

    public TaUser registerUser(TaUserDTO TAUserDTO) {
        String hashedPassword = passwordEncoder.encode(TAUserDTO.getPassword());

        TaUser user = new TaUser();
        user.setFirstName(TAUserDTO.getFirstName());
        user.setLastName(TAUserDTO.getLastName());
        user.setUsername(TAUserDTO.getUsername());
        user.setEmail(TAUserDTO.getEmail());
        user.setPhone(TAUserDTO.getPhone());
        user.setPassword(hashedPassword);
        user.setIsActive(TAUserDTO.getIsActive());
        user.setCreatedDate(LocalDateTime.now());

        return userRepository.save(user);
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
            Cipher cipher = Cipher.getInstance(SECRET_KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(token.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting token", e);
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


    public String decryptToken(String encryptedToken) {
        try {
            Cipher cipher = Cipher.getInstance(SECRET_KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedToken));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting token", e);
        }
    }

    public boolean isTokenValid(TaUser user, String token) {
        String decryptedToken = decryptToken(user.getResetToken());
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