package com.app.repository.impl;

import com.app.movie.MovieGenre;
import com.app.movie_showing.MovieShowing;
import com.app.movie_showing.MovieShowingRepository;
import com.app.repository.jpa.JpaMovieShowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieShowingRepositoryImpl implements MovieShowingRepository {

    private final JpaMovieShowingRepository jpaMovieShowingRepository;

    @Override
    public Optional<MovieShowing> addOrUpdate(MovieShowing movieShowing) {
        return Optional.of(jpaMovieShowingRepository.save(movieShowing));
    }

    @Override
    public List<MovieShowing> addOrUpdateMany(List<MovieShowing> items) {
        return jpaMovieShowingRepository.saveAll(items);
    }

    @Override
    public Optional<MovieShowing> findById(Long id) {
        return jpaMovieShowingRepository.findById(id);
    }

    @Override
    public List<MovieShowing> findAll() {
        return jpaMovieShowingRepository.findAll();
    }

    @Override
    public List<MovieShowing> findAllById(List<Long> ids) {
        return jpaMovieShowingRepository.findAllById(ids);
    }

    @Override
    public Optional<MovieShowing> deleteById(Long id) {
        return jpaMovieShowingRepository
                .findById(id)
                .flatMap(movieShowing -> {
                    jpaMovieShowingRepository.deleteById(id);
                    return Optional.of(movieShowing);
                });
    }

    @Override
    public List<MovieShowing> deleteAllById(List<Long> ids) {
        List<MovieShowing> movieShowing = jpaMovieShowingRepository.findAllById(ids);
        jpaMovieShowingRepository.deleteAll(movieShowing);
        return movieShowing;
    }

    @Override
    public boolean deleteAll() {
        jpaMovieShowingRepository.deleteAll();
        return true;
    }

    @Override
    public Optional<MovieShowing> findMovieShowingByCinemaIdAndMovieIdAndRoomNumberAndMAndMovieShowingTime(Long cinemaId, Long movieId, Integer roomNumber, LocalDateTime movieShowingTime) {
        return jpaMovieShowingRepository
                .findMovieShowingByCinemaIdAndMovieIdAndRoomNumberAndMAndMovieShowingTime(
                        cinemaId, movieId, roomNumber, movieShowingTime
                );
    }

    @Override
    public List<MovieShowing> findMovieShowingsByMovieMovieGenreAndMovieShowingTimeBetweenAndCinemaCityAndCinemaName(MovieGenre movieGenre, LocalDateTime movieShowingTimeBeg, LocalDateTime movieShowingTimeEnd, String cinemaCity, String cinemaName) {
        return jpaMovieShowingRepository
                .findMovieShowingsByMovieMovieGenreAndMovieShowingTimeBetweenAndCinemaCityAndCinemaName(
                        movieGenre,
                        movieShowingTimeBeg,
                        movieShowingTimeEnd,
                        cinemaCity,
                        cinemaName
                );
    }
}
