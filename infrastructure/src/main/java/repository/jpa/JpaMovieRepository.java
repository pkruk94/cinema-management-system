package repository.jpa;

import movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMovieRepository extends JpaRepository<Movie, Long> {
}
