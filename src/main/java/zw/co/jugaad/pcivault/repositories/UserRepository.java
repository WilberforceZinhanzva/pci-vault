package zw.co.jugaad.pcivault.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.jugaad.pcivault.models.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
