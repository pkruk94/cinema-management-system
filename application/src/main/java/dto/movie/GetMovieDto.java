package dto.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movie.MovieGenre;

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
