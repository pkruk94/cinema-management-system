package com.app.validation.movie_showing;

import com.app.dto.movie_showing.UpdateMovieShowingDto;
import com.app.validation.generic.Validator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UpdateMovieShowingValidator implements Validator<UpdateMovieShowingDto> {
    @Override
    public Map<String, String> validate(UpdateMovieShowingDto item) {
        var errors = new HashMap<String, String>();

        if (Objects.isNull(item)) {
            errors.put("object", "object is null");
            return errors;
        }

        if (!isIdValid(item.getId())) {
            errors.put("id", "id is not valid");
        }

        if (!isMovieIdValid(item.getMovieId())) {
            errors.put("com.app.movie id", "com.app.movie id not valid");
        }

        if (!isCinemaIdValid(item.getCinemaId())) {
            errors.put("com.app.cinema id", "com.app.cinema id not valud");
        }

        if (!isMovieShowingTimeValid(item.getMovieShowingTime())) {
            errors.put("com.app.movie showing time", "com.app.movie showing time");
        }

        if (!isRoomNumberValid(item.getRoomNumber())) {
            errors.put("room number", "room number is not valid");
        }

        return errors;
    }

    private boolean isIdValid(Long id) {
        return id != null && id > 0;
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
