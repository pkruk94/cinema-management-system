package repository.jpa;

import cinema.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCinemaRepository extends JpaRepository<Cinema, Long> {
}
