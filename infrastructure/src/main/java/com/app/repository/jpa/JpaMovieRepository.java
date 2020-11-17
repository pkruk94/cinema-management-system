package com.app.repository.jpa;

import com.app.movie.Movie;
import com.app.movie.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JpaMovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByTitleContainsOrMovieGenreEqualsOrReleaseDateBetween(String title, MovieGenre movieGenre, LocalDate beg, LocalDate end);

    Optional<Movie> findMovieByTitle(String title);

    List<Movie> findMoviesByMovieGenre(MovieGenre movieGenre);

    Optional<Movie> findMovieByTitleAndReleaseDate(String title, LocalDate releaseDate);
}
