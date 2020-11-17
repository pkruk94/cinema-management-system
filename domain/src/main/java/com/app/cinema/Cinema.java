package com.app.cinema;

import com.app.base.BaseEntity;
import com.app.movie_showing.MovieShowing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @ElementCollection
    @CollectionTable(name = "cinema_rooms", joinColumns = {@JoinColumn(name = "room_number")})
    @Column(name = "room_number")
    private List<Integer> roomNumbers;

    @OneToMany(mappedBy = "cinema")
    private List<MovieShowing> movieShowings;
}
