package com.app.movie_showing;

import com.app.base.BaseEntity;
import com.app.cinema.Cinema;
import com.app.movie.Movie;
import com.app.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "movieShowing")
    private List<Ticket> tickets;
}
