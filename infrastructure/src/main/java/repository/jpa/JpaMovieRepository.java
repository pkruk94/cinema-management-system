package repository.jpa;

import movie.Movie;
import movie.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JpaMovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByTitleContainsOrMovieGenreEqualsOrReleaseDateBetween(String title, MovieGenre movieGenre, LocalDate beg, LocalDate end);
}
