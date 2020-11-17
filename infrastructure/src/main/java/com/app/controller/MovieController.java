package com.app.controller;

import com.app.dto.movie.*;
import com.app.dto.response.ResponseData;
import com.app.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Long> createMovie(@RequestBody CreateMovieDto createMovieDto) {
        return ResponseData.<Long>builder()
                .data(movieService.addMovie(createMovieDto))
                .build();
    }

    @PutMapping
    public ResponseData<Long> updateMovie(@RequestBody UpdateMovieDto updateMovieDto) {
        return ResponseData.<Long>builder()
                .data(movieService.updateMovie(updateMovieDto))
                .build();
    }

    @PutMapping("/addFav")
    public ResponseData<Boolean> addFavourite(UpdateFavouriteMovie updateFavouriteMovie) {
        return ResponseData.<Boolean>builder()
                .data(movieService.addMovieToFavourites(updateFavouriteMovie))
                .build();
    }

    @PutMapping("/remFav")
    public ResponseData<Boolean> removeFavourite(RemoveFavouriteMovie removeFavouriteMovie) {
        return ResponseData.<Boolean>builder()
                .data(movieService.removeMovieFromFavourites(removeFavouriteMovie))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseData<GetMovieDto> deleteMovie(@PathVariable Long id) {
        return ResponseData.<GetMovieDto>builder()
                .data(movieService.deleteMovieById(id))
                .build();
    }

    @GetMapping
    public ResponseData<List<GetMovieDto>> getAllMovies() {
        return ResponseData.<List<GetMovieDto>>builder()
                .data(movieService.getAllMovies())
                .build();
    }

    @GetMapping
    public ResponseData<List<GetMovieDto>> getAllMoviesWithFilter(MovieFilterData movieFilterData) {
        return ResponseData.<List<GetMovieDto>>builder()
                .data(movieService.getAllMoviesWithFilters(movieFilterData))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseData<GetMovieDto> getMovieById(@PathVariable Long id) {
        return ResponseData.<GetMovieDto>builder()
                .data(movieService.getMovieById(id))
                .build();
    }


}
