package com.app.service;

import com.app.cinema.CinemaRepository;
import com.app.dto.cinema.CreateCinemaDto;
import com.app.dto.cinema.GetCinemaDto;
import com.app.dto.cinema.UpdateCinemaDto;
import com.app.exception.CinemaServiceException;
import com.app.mapper.Mapper;
import com.app.validation.cinema.CreateCinemaValidator;
import com.app.validation.cinema.UpdateCinemaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public Long addCinema(CreateCinemaDto createCinemaDto) {
        var createCinemaValidator = new CreateCinemaValidator();
        var errors = createCinemaValidator.validate(createCinemaDto);
        if (!errors.isEmpty()) {
            var errorMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));

            throw new CinemaServiceException(errorMessage);
        }

        if (cinemaRepository.findByNameCityAndAddress(createCinemaDto.getName(), createCinemaDto.getCity(), createCinemaDto.getAddressLine()).isPresent()) {
            throw new CinemaServiceException("Such com.app.cinema already exists in database");
        }

        var cinema = Mapper.fromCreateCinemaToCinema(createCinemaDto);
        cinema = cinemaRepository.addOrUpdate(cinema).orElseThrow(() -> new CinemaServiceException("Cinema could not be added to database"));
        return cinema.getId();
    }

    public GetCinemaDto deleteCinemaById(Long id) {
        if (Objects.isNull(id)) {
            throw new CinemaServiceException("Id cannot be null");
        }

        return Mapper.fromCinemaToGetCinemaDto(
                cinemaRepository
                        .deleteById(id)
                        .orElseThrow(() -> new CinemaServiceException("Movie cold not be deleted")));
    }

    public Long updateCinema(UpdateCinemaDto updateCinemaDto) {
        var updateCinemaValidator = new UpdateCinemaValidator();
        var errors = updateCinemaValidator.validate(updateCinemaDto);
        if (!errors.isEmpty()) {
            var errorMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));

            throw new CinemaServiceException(errorMessage);
        }

        var cinema = cinemaRepository
                .findById(updateCinemaDto.getId())
                .orElseThrow(() -> new CinemaServiceException("Such com.app.movie does not exist in database"));

        cinema.setAddressLine(updateCinemaDto.getAddressLine());
        cinema.setCity(updateCinemaDto.getCity());
        cinema.setName(updateCinemaDto.getName());
        cinema.setRoomNumbers(updateCinemaDto.getRoomNumbers());
        cinema = cinemaRepository.addOrUpdate(cinema).orElseThrow(() -> new CinemaServiceException("Movie could not be saved to database"));
        return cinema.getId();
    }
}
