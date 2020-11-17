package com.app.dto.movie;

import com.app.movie.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateMovieDto {
    //TODO powtórka z create com.app.movie com.app.dto
    private Long id;
    private String title;
    private MovieGenre movieGenre;
    private LocalDate releaseDate;

}
