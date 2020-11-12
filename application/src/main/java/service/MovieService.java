package service;

import dto.movie.GetMovieDto;
import dto.movie.MovieFilterData;
import exception.MovieServiceException;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;
import movie.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private MovieRepository movieRepository;

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


}
