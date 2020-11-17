package com.app.movie;

import com.app.base.BaseEntity;
import com.app.movie_showing.MovieShowing;
import com.app.user.User;
import com.app.value_object.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "movie")
    private List<MovieShowing> movieShowings;

    @ManyToMany(mappedBy = "favouriteMovies")
    private Set<User> users;

    @Embedded
    private Money price;
}
