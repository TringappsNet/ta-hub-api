package tahub.sdapitahub.Repository;

import tahub.sdapitahub.Entity.TaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface TaUserRepository extends JpaRepository<TaUser, Long> {
    TaUser findByEmail(String email);

    TaUser findByResetToken(String resetPasswordToken);
}