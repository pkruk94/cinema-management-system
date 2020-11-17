package com.app.movie_showing;

import com.app.base.generic.CrudRepository;
import com.app.movie.MovieGenre;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieShowingRepository extends CrudRepository<MovieShowing, Long> {
    Optional<MovieShowing> findMovieShowingByCinemaIdAndMovieIdAndRoomNumberAndMAndMovieShowingTime(Long cinemaId, Long movieId, Integer roomNumber, LocalDateTime movieShowingTime);

    List<MovieShowing> findMovieShowingsByMovieMovieGenreAndMovieShowingTimeBetweenAndCinemaCityAndCinemaName(MovieGenre movieGenre, LocalDateTime movieShowingTimeBeg, LocalDateTime movieShowingTimeEnd, String cinemaCity, String cinemaName);
}
