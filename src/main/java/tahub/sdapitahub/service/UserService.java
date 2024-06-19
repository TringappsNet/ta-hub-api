package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.dto.User.UserDTO;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.repository.TaUserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private TaUserRepository userRepository;

    public List<TaUser> getAllUsers() {
        return userRepository.findAll();
    }

    public TaUser getUserById(Long id) {
        Optional<TaUser> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public TaUser createUser(UserDTO userCreateDTO) {
        TaUser taUser = new TaUser.Builder()
                .firstName(userCreateDTO.getFirstName())
                .lastName(userCreateDTO.getLastName())
                .username(userCreateDTO.getUsername())
                .email(userCreateDTO.getEmail())
                .phone(userCreateDTO.getPhone())
                .resetToken(userCreateDTO.getResetToken())
                .password(userCreateDTO.getPassword())
                .isActive(userCreateDTO.getIsActive())
                .currentSessionId(userCreateDTO.getCurrentSessionId())
                .lastLoginTime(userCreateDTO.getLastLoginTime())
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();

        return userRepository.save(taUser);
    }

    public TaUser updateUser(Long id, UserDTO userCreateDTO) {
        Optional<TaUser> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            TaUser existingUser = userOptional.get();
            existingUser.setFirstName(userCreateDTO.getFirstName());
            existingUser.setLastName(userCreateDTO.getLastName());
            existingUser.setUsername(userCreateDTO.getUsername());
            existingUser.setEmail(userCreateDTO.getEmail());
            existingUser.setPhone(userCreateDTO.getPhone());
            existingUser.setResetToken(userCreateDTO.getResetToken());
            existingUser.setPassword(userCreateDTO.getPassword());
            existingUser.setIsActive(userCreateDTO.getIsActive());
            existingUser.setCurrentSessionId(userCreateDTO.getCurrentSessionId());
            existingUser.setLastLoginTime(userCreateDTO.getLastLoginTime());
            existingUser.setLastUpdated(LocalDateTime.now());

            return userRepository.update(existingUser);
        } else {
            return null; // Or throw an exception, depending on your requirements
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}


