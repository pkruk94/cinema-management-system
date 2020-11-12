package repository.impl;

import lombok.RequiredArgsConstructor;
import movie_showing.MovieShowing;
import movie_showing.MovieShowingRepository;
import org.springframework.stereotype.Repository;
import repository.jpa.JpaMovieShowingRepository;

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
}
