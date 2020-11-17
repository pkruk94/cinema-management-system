package com.app.dto.movie;

import com.app.movie.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMovieDto {

    private Long id;
    private String title;
    private MovieGenre movieGenre;
    private LocalDate releaseDate;
}
