package repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import user.User;

public interface JpaUserRepository extends JpaRepository<User, Long> {
}
