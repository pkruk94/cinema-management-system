package com.app.controller;

import com.app.dto.cinema.CreateCinemaDto;
import com.app.dto.cinema.GetCinemaDto;
import com.app.dto.cinema.UpdateCinemaDto;
import com.app.dto.response.ResponseData;
import com.app.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinemas")
@RequiredArgsConstructor
public class CinemaController {
    //TODO czy robic walidację dla kontrolerów?

    private final CinemaService cinemaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Long> createCinema(@RequestBody CreateCinemaDto createCinemaDto) {
        return ResponseData.<Long>builder()
                .data(cinemaService.addCinema(createCinemaDto))
                .build();
    }

    @PutMapping
    public ResponseData<Long> update(@RequestBody UpdateCinemaDto updateCinemaDto) {
        return ResponseData.<Long>builder()
                .data(cinemaService.updateCinema(updateCinemaDto))
                .build();
    }

    @DeleteMapping("/{idx}")
    public ResponseData<GetCinemaDto> delete(@PathVariable Long idx) {
        return ResponseData.<GetCinemaDto>builder()
                .data(cinemaService.deleteCinemaById(idx))
                .build();
    }
}
