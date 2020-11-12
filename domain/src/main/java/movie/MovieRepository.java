package movie;

import base.generic.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findAllByTitleContainsOrMovieGenreEqualsOrReleaseDateBetween(String title, MovieGenre movieGenre, LocalDate beg, LocalDate end);
}
