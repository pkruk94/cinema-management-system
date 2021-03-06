package com.app.repository.impl;

import com.app.movie.Movie;
import com.app.movie.MovieGenre;
import com.app.movie.MovieRepository;
import com.app.repository.jpa.JpaMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    @Override
    public List<Movie> findAllByTitleContainsOrMovieGenreEqualsOrReleaseDateBetween(String title, MovieGenre movieGenre, LocalDate beg, LocalDate end) {
        return jpaMovieRepository.findAllByTitleContainsOrMovieGenreEqualsOrReleaseDateBetween(title, movieGenre, beg, end);
    }

    @Override
    public Optional<Movie> findMovieByTitle(String title) {
        return jpaMovieRepository.findMovieByTitle(title);
    }

    @Override
    public List<Movie> findMovieByGenre(MovieGenre movieGenre) {
        return jpaMovieRepository.findMoviesByMovieGenre(movieGenre);
    }

    @Override
    public Optional<Movie> findMovieByTitleAndReleaseDate(String title, LocalDate releaseDate) {
        return jpaMovieRepository.findMovieByTitleAndReleaseDate(title, releaseDate);
    }
}
