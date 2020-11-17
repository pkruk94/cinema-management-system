package com.app.dto.movie_showing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMovieShowingDto {

    private Long id;
    private Long movieId;
    private LocalDateTime movieShowingTime;
    private Integer roomNumber;
    private Long cinemaId;
}
