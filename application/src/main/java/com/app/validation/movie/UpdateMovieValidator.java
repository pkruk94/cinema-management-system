package com.app.validation.movie;

import com.app.dto.movie.UpdateMovieDto;
import com.app.movie.MovieGenre;
import com.app.validation.generic.Validator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UpdateMovieValidator implements Validator<UpdateMovieDto> {
    @Override
    public Map<String, String> validate(UpdateMovieDto item) {
        var errors = new HashMap<String, String>();

        if (Objects.isNull(item)) {
            errors.put("object", "object is null");
            return errors;
        }

        if (!isIdValid(item.getId())) {
            errors.put("id", "id is not valid");
        }

        if (!isMovieGenreValid(item.getMovieGenre())) {
            errors.put("com.app.movie genre", "com.app.movie genre is not valid");
        }

        if (!isTitleValid(item.getTitle())) {
            errors.put("title", "com.app.movie title is not valid");
        }

        if (!isMovieReleaseDateValid(item.getReleaseDate())) {
            errors.put("release date", "com.app.movie release date is not valid");
        }

        return errors;
    }

    private boolean isIdValid(Long id) {
        return id != null && id > 0;
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
