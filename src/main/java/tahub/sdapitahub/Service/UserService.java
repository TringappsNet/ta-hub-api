package tahub.sdapitahub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.Entity.TaUser;
import tahub.sdapitahub.Repository.TaUserRepository;

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

    public TaUser createUser(TaUser user) {
        user.setCreatedDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    public TaUser updateUser(Long id, TaUser user) {
        Optional<TaUser> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            user.setUserId(id);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
