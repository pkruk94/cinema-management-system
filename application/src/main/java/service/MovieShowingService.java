package service;

import cinema.Cinema;
import cinema.CinemaRepository;
import dto.movie_showing.CreateMovieShowingDto;
import dto.movie_showing.GetMovieShowingDto;
import dto.movie_showing.MovieShowingFilterData;
import dto.movie_showing.UpdateMovieShowingDto;
import exception.MovieServiceException;
import exception.MovieShowingServiceException;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;
import movie.Movie;
import movie.MovieRepository;
import movie_showing.MovieShowingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import validation.movie_showing.CreateMovieShowingValidator;
import validation.movie_showing.UpdateMovieShowingValidator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieShowingService {

    private final MovieShowingRepository movieShowingRepository;
    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;

    public Long addMovieShowing(CreateMovieShowingDto createMovieShowingDto) {
        var createMovieShowingValidator = new CreateMovieShowingValidator();
        var errors = createMovieShowingValidator.validate(createMovieShowingDto);
        if (!errors.isEmpty()) {
            var errorMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));

            throw new MovieShowingServiceException(errorMessage);
        }

        if (movieShowingRepository
                .findMovieShowingByCinemaIdAndMovieIdAndRoomNumberAndMAndMovieShowingTime(
                        createMovieShowingDto.getCinemaId(),
                        createMovieShowingDto.getCinemaId(),
                        createMovieShowingDto.getRoomNumber(),
                        createMovieShowingDto.getMovieShowingTime()
                ).isPresent()) {
            throw new MovieServiceException("Such movie showing already exists in database");
        }

        var movieShowing = Mapper.fromCreateMovieShowingToMovieShowing(createMovieShowingDto);

        Cinema cinema = cinemaRepository
                .findById(createMovieShowingDto.getCinemaId())
                .orElseThrow(() -> new MovieShowingServiceException("Cinema could not be found"));

        if (!cinema.getRoomNumbers().contains(createMovieShowingDto.getRoomNumber())) {
            throw new MovieShowingServiceException("Such room number does not exist in this cinema");
        }

        Movie movie = movieRepository
                .findById(createMovieShowingDto.getMovieId())
                .orElseThrow(() -> new MovieShowingServiceException("Movie could not be found"));

        movieShowing.setCinema(cinema);
        movieShowing.setMovie(movie);

        return movieShowingRepository
                .addOrUpdate(movieShowing)
                .orElseThrow(() -> new MovieShowingServiceException("Movie showing could not be saved to db"))
                .getId();
    }

    public GetMovieShowingDto deleteMovieShowingById(Long id) {
        if (Objects.isNull(id)) {
            throw new MovieShowingServiceException("Id cannot be null");
        }

        return Mapper.fromMovieShowingToGetMovieShowingDto(
                movieShowingRepository
                        .deleteById(id)
                        .orElseThrow(() -> new MovieShowingServiceException("Movie cold not be deleted")));
    }

    public Long updateMovie(UpdateMovieShowingDto updateMovieShowingDto) {
        var updateMovieShowingValidator = new UpdateMovieShowingValidator();
        var errors = updateMovieShowingValidator.validate(updateMovieShowingDto);
        if (!errors.isEmpty()) {
            var errorMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));

            throw new MovieShowingServiceException(errorMessage);
        }

        var movieShowing = movieShowingRepository
                .findById(updateMovieShowingDto.getId())
                .orElseThrow(() -> new MovieServiceException("Movie showing could not be found"));

        Cinema cinema = cinemaRepository
                .findById(updateMovieShowingDto.getCinemaId())
                .orElseThrow(() -> new MovieShowingServiceException("Cinema could not be found"));

        if (!cinema.getRoomNumbers().contains(updateMovieShowingDto.getRoomNumber())) {
            throw new MovieShowingServiceException("Such room number does not exist in this cinema");
        }

        Movie movie = movieRepository
                .findById(updateMovieShowingDto.getMovieId())
                .orElseThrow(() -> new MovieShowingServiceException("Movie could not be found"));

        movieShowing.setCinema(cinema);
        movieShowing.setMovie(movie);
        movieShowing.setMovieShowingTime(updateMovieShowingDto.getMovieShowingTime());
        movieShowing.setRoomNumber(updateMovieShowingDto.getRoomNumber());
        return movieShowingRepository
                .addOrUpdate(movieShowing)
                .orElseThrow(() -> new MovieServiceException("Movie showing could not be saved to database"))
                .getId();
    }

    public List<GetMovieShowingDto> getAllMovieShowings() {
        return movieShowingRepository
                .findAll()
                .stream()
                .map(Mapper::fromMovieShowingToGetMovieShowingDto)
                .collect(Collectors.toList());
    }

    public List<GetMovieShowingDto> getAllMoviesWithFilters(MovieShowingFilterData movieShowingFilterData) {
        return movieShowingRepository
                .findMovieShowingsByMovieMovieGenreAndMovieShowingTimeBetweenAndCinemaCityAndCinemaName(
                        movieShowingFilterData.getMovieGenre(),
                        movieShowingFilterData.getMovieShowingTimeBeg(),
                        movieShowingFilterData.getMovieShowingTimeEnd(),
                        movieShowingFilterData.getCinemaCity(),
                        movieShowingFilterData.getCinemaCity()
                )
                .stream()
                .map(Mapper::fromMovieShowingToGetMovieShowingDto)
                .collect(Collectors.toList());
    }

    public GetMovieShowingDto getMovieShowingById(Long id) {
        if (Objects.isNull(id)) {
            throw new MovieShowingServiceException("Movie ID cannot be null");
        }

        return Mapper.fromMovieShowingToGetMovieShowingDto(
                movieShowingRepository.findById(id).orElseThrow(() -> new MovieShowingServiceException("Movie showing could not be found"))
        );
    }
}
