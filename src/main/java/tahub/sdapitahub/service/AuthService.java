package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.exception.UserAlreadyExistsException;
import tahub.sdapitahub.exception.UserNotFoundException;
import tahub.sdapitahub.repository.TaUserRepository;
import tahub.sdapitahub.Utils.MailUtil;
import tahub.sdapitahub.Utils.TokenUtil;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private TaUserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Optional<TaUser> findUserByInviteToken(String inviteToken) {
        return userRepository.findByInviteToken(inviteToken);
    }

    public TaUser registerUser(TaUser taUser) {
        String hashedPassword = passwordEncoder.encode(taUser.getPassword());
        TaUser existingUser = userRepository.findByEmail(taUser.getEmail());
        if (existingUser != null) {
            taUser.setPassword(hashedPassword);
            existingUser.setIsActive(true);
            existingUser.setLastUpdated(LocalDateTime.now());
            userRepository.update(existingUser);
        } else {
            taUser.setPassword(hashedPassword);
            taUser.setIsActive(true);
            taUser.setCreatedAt(LocalDateTime.now());
            taUser.setLastUpdated(LocalDateTime.now());
            return userRepository.update(taUser);
        }
        return taUser;
    }


    public void forgetPassword(String email) {
        TaUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with email: " + email + "not found");
        }
        String token = TokenUtil.generateRandomString();
        user.setResetToken(token);
        userRepository.update(user);

        String subject = "Password Reset Request";
        String text = "To reset your password, please visit the following link: " +
                MailUtil.BASE_URL + "/reset-new?token=" + token;
        MailUtil.sendMail(user.getEmail(), subject, text);
    }


    public TaUser sendInvitation(String email, Long roleId) {

        TaUser existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with email: " + email + " already exists.");
        }

        String inviteToken = TokenUtil.generateRandomString();
        TaUser.Builder builder = new TaUser.Builder();
            TaUser user = builder
                    .email(email)
                    .roleId(roleId)
                    .inviteToken(inviteToken)
                    .build();
            userRepository.create(user);

        // Send invitation email
        String subject = "Invitation to Register";
        String text = "You have been invited to register into TA-Hub. Please use the following link to complete your registration: " + MailUtil.BASE_URL + "/register?token=" + inviteToken;
        MailUtil.sendMail(email, subject, text);

        return user;
    }


    public TaUser resetPassword(TaUser user, String newPassword) {
        user.setPassword(encodePassword(newPassword));
        user.setResetToken(null);
        return updateUser(user);
    }

    public TaUser findUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    public boolean validateInviteToken(String inviteToken) {
        Optional<TaUser> user = userRepository.findByInviteToken(inviteToken);
        return user.isPresent();
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

    public TaUser updateUser(TaUser user) {
        return userRepository.update(user);
    }

}
