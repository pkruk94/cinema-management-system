package cinema;

import base.generic.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {
    Optional<Cinema> findByNameCityAndAddress(String name, String city, String address);

    List<Cinema> findCityWithHighestNumberOfCustomers();
}
