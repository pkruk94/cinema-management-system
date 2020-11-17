package com.app.repository.jpa;

import com.app.cinema.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaCinemaRepository extends JpaRepository<Cinema, Long> {
    Optional<Cinema> findCinemaByNameAndCityAndAddressLine(String name, String city, String addressLine);

    //TODO
    List<Cinema> findCityWithHighestNumberOfCustomers();
}
