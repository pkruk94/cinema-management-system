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
public class MovieFilterData {

    private String title;
    private MovieGenre movieGenre;
    private LocalDate releaseDateBeg;
    private LocalDate releaseDateEnd;
}
