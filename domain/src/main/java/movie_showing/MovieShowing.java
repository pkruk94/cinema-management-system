package movie_showing;

import base.BaseEntity;
import cinema.Cinema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movie.Movie;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movie_showings")
public class MovieShowing extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Movie movie;

    private LocalDateTime movieShowingTime;
    private Integer roomNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Cinema cinema;
}
