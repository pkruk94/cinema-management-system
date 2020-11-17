package com.app.movie;

import com.app.base.generic.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findAllByTitleContainsOrMovieGenreEqualsOrReleaseDateBetween(String title, MovieGenre movieGenre, LocalDate beg, LocalDate end);

    Optional<Movie> findMovieByTitle(String title);

    List<Movie> findMovieByGenre(MovieGenre movieGenre);

    Optional<Movie> findMovieByTitleAndReleaseDate(String title, LocalDate releaseDate);
}
