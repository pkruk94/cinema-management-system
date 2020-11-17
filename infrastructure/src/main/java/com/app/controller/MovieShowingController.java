package com.app.controller;

import com.app.dto.movie_showing.CreateMovieShowingDto;
import com.app.dto.movie_showing.GetMovieShowingDto;
import com.app.dto.movie_showing.MovieShowingFilterData;
import com.app.dto.movie_showing.UpdateMovieShowingDto;
import com.app.dto.response.ResponseData;
import com.app.service.MovieShowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showings")
@RequiredArgsConstructor
public class MovieShowingController {

    private final MovieShowingService movieShowingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Long> addMovieShowing(@RequestBody CreateMovieShowingDto createMovieShowingDto) {
        return ResponseData.<Long>builder()
                .data(movieShowingService.addMovieShowing(createMovieShowingDto))
                .build();
    }

    @PutMapping
    public ResponseData<Long> updateMovieShowing(@RequestBody UpdateMovieShowingDto updateMovieShowingDto) {
        return ResponseData.<Long>builder()
                .data(movieShowingService.updateMovieShowing(updateMovieShowingDto))
                .build();
    }

    @DeleteMapping("/{idx}")
    public ResponseData<GetMovieShowingDto> deleteMovieShowingById(@PathVariable Long idx) {
        return ResponseData.<GetMovieShowingDto>builder()
                .data(movieShowingService.deleteMovieShowingById(idx))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseData<GetMovieShowingDto> getOneByIndex(@PathVariable("id") Long id) {
        return ResponseData.<GetMovieShowingDto>builder()
                .data(movieShowingService.getMovieShowingById(id))
                .build();
    }

    @GetMapping
    public ResponseData<List<GetMovieShowingDto>> getAllMovieShowings() {
        return ResponseData.<List<GetMovieShowingDto>>builder()
                .data(movieShowingService.getAllMovieShowings())
                .build();
    }

    @GetMapping
    public ResponseData<List<GetMovieShowingDto>> getAllMovieShowingsWithFilter(@RequestBody MovieShowingFilterData movieShowingFilterData) {
        return ResponseData.<List<GetMovieShowingDto>>builder()
                .data(movieShowingService.getAllMoviesWithFilters(movieShowingFilterData))
                .build();
    }
}
