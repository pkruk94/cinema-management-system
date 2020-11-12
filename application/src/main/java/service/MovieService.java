package service;

import dto.movie.CreateMovieDto;
import dto.movie.GetMovieDto;
import dto.movie.MovieFilterData;
import exception.MovieServiceException;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;
import movie.Movie;
import movie.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.User;
import user.UserRepository;
import validation.CreateMovieValidator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private MovieRepository movieRepository;
    private UserRepository userRepository;

    public Long addMovie(CreateMovieDto createMovieDto) {
        var createMovieValidator = new CreateMovieValidator();
        var errors = createMovieValidator.validate(createMovieDto);
        if (!errors.isEmpty()) {
            var errorMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));

            throw new MovieServiceException(errorMessage);
        }

        var movie = Mapper.fromCreateMovieToMovie(createMovieDto);
        // TODO potrzebny update?
        movie = movieRepository.addOrUpdate(movie).orElseThrow(() -> new MovieServiceException("Movie could not be added to database"));
        return movie.getId();
    }

    public List<GetMovieDto> getAllMovies() {
        return movieRepository
                .findAll()
                .stream()
                .map(Mapper::fromMovieToGetMovieDto)
                .collect(Collectors.toList());
    }

    public List<GetMovieDto> getAllMoviesWithFilters(MovieFilterData movieFilterData) {
        return movieRepository
                .findAllByTitleContainsOrMovieGenreEqualsOrReleaseDateBetween(
                        movieFilterData.getTitle(),
                        movieFilterData.getMovieGenre(),
                        movieFilterData.getReleaseDateBeg(),
                        movieFilterData.getReleaseDateEnd()
                )
                .stream()
                .map(Mapper::fromMovieToGetMovieDto)
                .collect(Collectors.toList());
    }

    public GetMovieDto getMovieById(Long id) {
        if (Objects.isNull(id)) {
            throw new MovieServiceException("Movie ID cannot be null");
        }

        return Mapper.fromMovieToGetMovieDto(
                movieRepository.findById(id).orElseThrow(() -> new MovieServiceException("Movie could not be found"))
        );
    }

    public boolean addMovieToFavourites(Long movieId, Long userId) {
        if (Objects.isNull(movieId) || Objects.isNull(userId)) {
            throw new MovieServiceException("Neither movie ID nor user ID cannot be null");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new MovieServiceException("User could not be found while assigning favourite movie"));
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieServiceException("Movie could not be found while assigning to user"));

        user.getFavouriteMovies().add(movie);
        // TODO potrzebne?
        userRepository.addOrUpdate(user);
        return true;
    }

}
