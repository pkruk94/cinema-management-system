package movie;

import base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movie_showing.MovieShowing;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class Movie extends BaseEntity {

    private String title;

    @Enumerated(EnumType.STRING)
    private MovieGenre movieGenre;

    private LocalDate releaseDate;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private MovieShowing movieShowings;
}
