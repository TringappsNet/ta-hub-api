package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public TaUser createUser(TaUser user) {
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public TaUser updateUser(Long id, TaUser user) {
        user.setUserId(id);
        return userRepository.update(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
