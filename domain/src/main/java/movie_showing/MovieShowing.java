package movie_showing;

import cinema.Cinema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movie.Movie;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movie_showings")
public class MovieShowing {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "movieShowings")
    private List<Movie> movie;

    private LocalDateTime movieShowingTime;
    private Integer roomNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Cinema cinema;
}
