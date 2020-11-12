package repository.jpa;

import movie.MovieGenre;
import movie_showing.MovieShowing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaMovieShowingRepository extends JpaRepository<MovieShowing, Long> {
    Optional<MovieShowing> findMovieShowingByCinemaIdAndMovieIdAndRoomNumberAndMAndMovieShowingTime(Long cinemaId, Long movieId, Integer roomNumber, LocalDateTime movieShowingTime);

    List<MovieShowing> findMovieShowingsByMovieMovieGenreAndMovieShowingTimeBetweenAndCinemaCityAndCinemaName(MovieGenre movieGenre, LocalDateTime movieShowingTimeBeg, LocalDateTime movieShowingTimeEnd, String cinemaCity, String cinemaName);
}
