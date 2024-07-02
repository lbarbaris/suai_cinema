package ru.lbarbaris.webservice.security.securityRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lbarbaris.webservice.security.securityEntity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String username);
}
