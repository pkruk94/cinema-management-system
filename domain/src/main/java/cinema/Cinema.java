package cinema;

import base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movie_showing.MovieShowing;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cinemas")
public class Cinema extends BaseEntity {

    private String name;
    private String city;
    private String addressLine;

    @OneToMany(mappedBy = "cinema")
    private List<MovieShowing> movieShowings;
}
