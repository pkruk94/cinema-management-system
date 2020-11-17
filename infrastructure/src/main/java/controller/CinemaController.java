package controller;

import dto.cinema.CreateCinemaDto;
import dto.cinema.GetCinemaDto;
import dto.cinema.UpdateCinemaDto;
import dto.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.CinemaService;

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
