package tahub.sdapitahub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.Entity.TAUser;
import tahub.sdapitahub.Repository.TAUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private TAUserRepository userRepository;

    public List<TAUser> getAllUsers() {
        return userRepository.findAll();
    }

    public TAUser getUserById(Long id) {
        Optional<TAUser> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public TAUser createUser(TAUser user) {
        return userRepository.save(user);
    }

    public TAUser updateUser(Long id, TAUser user) {
        Optional<TAUser> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            user.setUserID(id);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
