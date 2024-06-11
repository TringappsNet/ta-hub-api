package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.dto.UserCreateDTO; // Ensure this import is correct
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

    public TaUser createUser(UserCreateDTO userCreateDTO) {
        // Set the created and last updated timestamps
        userCreateDTO.setCreatedAt(LocalDateTime.now());
        userCreateDTO.setLastUpdated(LocalDateTime.now());

        // Convert UserCreateDTO to TaUser
        TaUser taUser = new TaUser.Builder()
                .userId(userCreateDTO.getUserId())
                .firstName(userCreateDTO.getFirstName())
                .lastName(userCreateDTO.getLastName()) // Assuming you have a lastName field
                .email(userCreateDTO.getEmail()) // Assuming you have an email field
                .createdAt(userCreateDTO.getCreatedAt())
                .lastUpdated(userCreateDTO.getLastUpdated())
                .build();

        // Save the TaUser entity to the repository
        return userRepository.save(taUser);
    }

    public TaUser updateUser(Long id, TaUser user) {
        user.setUserId(id);
        user.setLastUpdated(LocalDateTime.now());
        return userRepository.save(user); // Assuming `update` method in repository is actually `save`
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
