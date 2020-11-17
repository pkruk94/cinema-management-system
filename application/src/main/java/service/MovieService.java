package service;

import dto.movie.*;
import exception.MovieServiceException;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;
import movie.Movie;
import movie.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.User;
import user.UserRepository;
import validation.movie.CreateMovieValidator;
import validation.movie.UpdateMovieValidator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    // TODO dodac opcje usuwania, edycji, dodawania dla wielu i dla innych klas
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

        if (movieRepository.findMovieByTitleAndReleaseDate(createMovieDto.getTitle(), createMovieDto.getReleaseDate()).isPresent()) {
            throw new MovieServiceException("Such movie already exists in database");
        }

        var movie = Mapper.fromCreateMovieToMovie(createMovieDto);
        // TODO potrzebny update?
        return movieRepository
                .addOrUpdate(movie)
                .orElseThrow(() -> new MovieServiceException("Movie could not be added to database"))
                .getId();
    }

    public List<Long> addMovieMany(List<CreateMovieDto> createMovieDtos) {
        // TODO zptymalizować
        if (Objects.isNull(createMovieDtos)) {
            throw new MovieServiceException("Movie list cannot be null");
        }

        var createMovieValidator = new CreateMovieValidator();
        for (CreateMovieDto createMovieDto : createMovieDtos) {
            var errors = createMovieValidator.validate(createMovieDto);
            if (!errors.isEmpty()) {
                var errorMessage = errors
                        .entrySet()
                        .stream()
                        .map(e -> e.getKey() + ": " + e.getValue())
                        .collect(Collectors.joining(", "));

                throw new MovieServiceException(errorMessage);
            }
        }

        for (CreateMovieDto createMovieDto : createMovieDtos) {
            if (movieRepository.findMovieByTitleAndReleaseDate(createMovieDto.getTitle(), createMovieDto.getReleaseDate()).isPresent()) {
                throw new MovieServiceException("Such movie already exists in database");
            }
        }

        return createMovieDtos
                .stream()
                .map(Mapper::fromCreateMovieToMovie)
                .map(movie -> movieRepository.addOrUpdate(movie))
                .filter(Optional::isPresent)
                .map(movie -> movie.get().getId())
                .collect(Collectors.toList());
    }

    public GetMovieDto deleteMovieById(Long id) {
        if (Objects.isNull(id)) {
            throw new MovieServiceException("Id cannot be null");
        }

        return Mapper.fromMovieToGetMovieDto(
                movieRepository
                        .deleteById(id)
                        .orElseThrow(() -> new MovieServiceException("Movie cold not be deleted")));
    }

    public Long updateMovie(UpdateMovieDto updateMovieDto) {
        var updateMovieValidator = new UpdateMovieValidator();
        var errors = updateMovieValidator.validate(updateMovieDto);
        if (!errors.isEmpty()) {
            var errorMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));

            throw new MovieServiceException(errorMessage);
        }

        var movie = movieRepository
                .findById(updateMovieDto.getId())
                .orElseThrow(() -> new MovieServiceException("Such movie does not exist in database"));

        movie.setMovieGenre(updateMovieDto.getMovieGenre());
        movie.setReleaseDate(updateMovieDto.getReleaseDate());
        movie.setTitle(updateMovieDto.getTitle());
        movie = movieRepository.addOrUpdate(movie).orElseThrow(() -> new MovieServiceException("Movie could not be saved to database"));
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

    public boolean addMovieToFavourites(UpdateFavouriteMovie updateFavouriteMovie) {
        if (Objects.isNull(updateFavouriteMovie.getMovieId()) || Objects.isNull(updateFavouriteMovie.getUserId())) {
            throw new MovieServiceException("Neither movie ID nor user ID cannot be null");
        }

        User user = userRepository.findById(updateFavouriteMovie.getUserId()).orElseThrow(() -> new MovieServiceException("User could not be found while assigning favourite movie"));
        Movie movie = movieRepository.findById(updateFavouriteMovie.getMovieId()).orElseThrow(() -> new MovieServiceException("Movie could not be found while assigning to user"));

        user.getFavouriteMovies().add(movie);
        // TODO potrzebne?
        userRepository.addOrUpdate(user);
        return true;
    }

    public boolean removeMovieFromFavourites(RemoveFavouriteMovie removeFavouriteMovie) {
        if (Objects.isNull(removeFavouriteMovie.getMovieId()) || Objects.isNull(removeFavouriteMovie.getUserId())) {
            throw new MovieServiceException("Neither movie ID nor user ID cannot be null");
        }

        User user = userRepository.findById(removeFavouriteMovie.getUserId()).orElseThrow(() -> new MovieServiceException("User could not be found while assigning favourite movie"));
        Movie movie = movieRepository.findById(removeFavouriteMovie.getMovieId()).orElseThrow(() -> new MovieServiceException("Movie could not be found while assigning to user"));

        user.getFavouriteMovies().remove(movie);
        userRepository.addOrUpdate(user);
        return true;
    }

    public boolean sendInfoAboutNewMovies() {
        // TODO
        return true;
    }

    public boolean sendInfoAboutFavouriteMovies(Long movieID) {
        // TODO
        return true;
    }

}
