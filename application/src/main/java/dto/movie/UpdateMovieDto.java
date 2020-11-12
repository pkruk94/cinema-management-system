package dto.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movie.MovieGenre;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateMovieDto {
    //TODO powtórka z create movie dto
    private Long id;
    private String title;
    private MovieGenre movieGenre;
    private LocalDate releaseDate;

}
