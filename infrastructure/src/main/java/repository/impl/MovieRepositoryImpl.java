package repository.impl;

import lombok.RequiredArgsConstructor;
import movie.Movie;
import movie.MovieRepository;
import org.springframework.stereotype.Repository;
import repository.jpa.JpaMovieRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {

    private final JpaMovieRepository jpaMovieRepository;

    @Override
    public Optional<Movie> addOrUpdate(Movie movie) {
        return Optional.of(jpaMovieRepository.save(movie));
    }

    @Override
    public List<Movie> addOrUpdateMany(List<Movie> items) {
        return jpaMovieRepository.saveAll(items);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return jpaMovieRepository.findById(id);
    }

    @Override
    public List<Movie> findAll() {
        return jpaMovieRepository.findAll();
    }

    @Override
    public List<Movie> findAllById(List<Long> ids) {
        return jpaMovieRepository.findAllById(ids);
    }

    @Override
    public Optional<Movie> deleteById(Long id) {
        return jpaMovieRepository
                .findById(id)
                .flatMap(movie -> {
                    jpaMovieRepository.deleteById(id);
                    return Optional.of(movie);
                });
    }

    @Override
    public List<Movie> deleteAllById(List<Long> ids) {
        List<Movie> movies = jpaMovieRepository.findAllById(ids);
        jpaMovieRepository.deleteAll(movies);
        return movies;
    }

    @Override
    public boolean deleteAll() {
        jpaMovieRepository.deleteAll();
        return true;
    }
}
