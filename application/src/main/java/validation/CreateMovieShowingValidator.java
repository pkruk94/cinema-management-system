package validation;

import dto.movie_showing.CreateMovieShowingDto;
import validation.generic.Validator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateMovieShowingValidator implements Validator<CreateMovieShowingDto> {
    @Override
    public Map<String, String> validate(CreateMovieShowingDto item) {
        var errors = new HashMap<String, String>();

        if (Objects.isNull(item)) {
            errors.put("object", "object is null");
            return errors;
        }

        if (!isMovieIdValid(item.getMovieId())) {
            errors.put("movie id", "movie id not valid");
        }

        if (!isCinemaIdValid(item.getCinemaId())) {
            errors.put("cinema id", "cinema id not valud");
        }

        if (!isMovieShowingTimeValid(item.getMovieShowingTime())) {
            errors.put("movie showing time", "movie showing time");
        }

        if (!isRoomNumberValid(item.getRoomNumber())) {
            errors.put("room number", "room number is not valid");
        }

        return errors;
    }

    private boolean isRoomNumberValid(Integer roomNumber) {
        return roomNumber != null && roomNumber > 0;
    }

    private boolean isMovieShowingTimeValid(LocalDateTime movieShowingTime) {
        return movieShowingTime != null;
    }

    private boolean isCinemaIdValid(Long cinemaId) {
        return cinemaId != null && cinemaId > 0;
    }

    private boolean isMovieIdValid(Long movieId) {
        return movieId != null && movieId > 0;
    }
}
