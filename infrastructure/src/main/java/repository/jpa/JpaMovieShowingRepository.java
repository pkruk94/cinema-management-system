package repository.jpa;

import movie_showing.MovieShowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMovieShowingRepository extends JpaRepository<MovieShowing, Long> {
}
