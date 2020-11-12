package repository.jpa;

import cinema.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCinemaRepository extends JpaRepository<Cinema, Long> {
    Optional<Cinema> findCinemaByNameAndCityAndAddressLine(String name, String city, String addressLine);
}
