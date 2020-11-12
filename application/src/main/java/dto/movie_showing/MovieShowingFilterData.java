package dto.movie_showing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movie.MovieGenre;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieShowingFilterData {

    private MovieGenre movieGenre;
    private LocalDateTime movieShowingTimeBeg;
    private LocalDateTime movieShowingTimeEnd;
    private String cinemaName;
    private String cinemaCity;

}
