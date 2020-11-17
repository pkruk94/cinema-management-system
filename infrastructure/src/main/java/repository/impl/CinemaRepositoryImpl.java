package repository.impl;

import cinema.Cinema;
import cinema.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import repository.jpa.JpaCinemaRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CinemaRepositoryImpl implements CinemaRepository {

    private final JpaCinemaRepository jpaCinemaRepository;

    @Override
    public Optional<Cinema> addOrUpdate(Cinema cinema) {
        return Optional.of(jpaCinemaRepository.save(cinema));
    }

    @Override
    public List<Cinema> addOrUpdateMany(List<Cinema> items) {
        return jpaCinemaRepository.saveAll(items);
    }

    @Override
    public Optional<Cinema> findById(Long id) {
        return jpaCinemaRepository.findById(id);
    }

    @Override
    public List<Cinema> findAll() {
        return jpaCinemaRepository.findAll();
    }

    @Override
    public List<Cinema> findAllById(List<Long> ids) {
        return jpaCinemaRepository.findAllById(ids);
    }

    @Override
    public Optional<Cinema> deleteById(Long id) {
        return jpaCinemaRepository
                .findById(id)
                .flatMap(cinema -> {
                    jpaCinemaRepository.deleteById(id);
                    return Optional.of(cinema);
                });
    }

    @Override
    public List<Cinema> deleteAllById(List<Long> ids) {
        List<Cinema> cinemas = jpaCinemaRepository.findAllById(ids);
        jpaCinemaRepository.deleteAll(cinemas);
        return cinemas;
    }

    @Override
    public boolean deleteAll() {
        jpaCinemaRepository.deleteAll();
        return true;
    }

    @Override
    public Optional<Cinema> findByNameCityAndAddress(String name, String city, String address) {
        return jpaCinemaRepository.findCinemaByNameAndCityAndAddressLine(name, city, address);
    }

    @Override
    public List<Cinema> findCityWithHighestNumberOfCustomers() {
        return jpaCinemaRepository.findCityWithHighestNumberOfCustomers();
    }
}
