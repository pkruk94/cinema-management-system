package validation;

import dto.movie.CreateMovieDto;
import movie.MovieGenre;
import validation.generic.Validator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateMovieValidator implements Validator<CreateMovieDto> {
    @Override
    public Map<String, String> validate(CreateMovieDto item) {
        var errors = new HashMap<String, String>();

        if (Objects.isNull(item)) {
            errors.put("object", "object is null");
            return errors;
        }

        if (!isMovieGenreValid(item.getMovieGenre())) {
            errors.put("movie genre", "movie genre is not valid");
        }

        if (!isTitleValid(item.getTitle())) {
            errors.put("title", "movie title is not valid");
        }

        if (!isMovieReleaseDateValid(item.getReleaseDate())) {
            errors.put("release date", "movie release date is not valid");
        }

        return errors;
    }

    private boolean isTitleValid(String title) {
        return title != null && title.matches("[A-z0-9 ]+");
    }

    private boolean isMovieReleaseDateValid(LocalDate releaseDate) {
        return releaseDate != null;
    }

    private boolean isMovieGenreValid(MovieGenre movieGenre) {
        return movieGenre != null;
    }
}
