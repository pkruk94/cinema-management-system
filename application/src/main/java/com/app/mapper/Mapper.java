package com.app.mapper;

import com.app.base.BaseEntity;
import com.app.cinema.Cinema;
import com.app.dto.cinema.CreateCinemaDto;
import com.app.dto.cinema.GetCinemaDto;
import com.app.dto.movie.CreateMovieDto;
import com.app.dto.movie.GetMovieDto;
import com.app.dto.movie_showing.CreateMovieShowingDto;
import com.app.dto.movie_showing.GetMovieShowingDto;
import com.app.dto.order.GetOrderDto;
import com.app.dto.user.CreateUserDto;
import com.app.dto.user.GetUserDto;
import com.app.movie.Movie;
import com.app.movie_showing.MovieShowing;
import com.app.order.Order;
import com.app.user.User;

import java.util.stream.Collectors;

public interface Mapper {

    static Movie fromCreateMovieToMovie(CreateMovieDto createMovieDto) {
        return Movie.builder()
                .movieGenre(createMovieDto.getMovieGenre())
                .title(createMovieDto.getTitle())
                .releaseDate(createMovieDto.getReleaseDate())
                .build();
    }

    static GetMovieDto fromMovieToGetMovieDto(Movie movie) {
        return GetMovieDto.builder()
                .id(movie.getId())
                .movieGenre(movie.getMovieGenre())
                .releaseDate(movie.getReleaseDate())
                .title(movie.getTitle())
                .build();
    }

    static Cinema fromCreateCinemaToCinema(CreateCinemaDto createCinemaDto) {
        return Cinema.builder()
                .city(createCinemaDto.getCity())
                .addressLine(createCinemaDto.getAddressLine())
                .roomNumbers(createCinemaDto.getRoomNumbers())
                .name(createCinemaDto.getName())
                .build();
    }

    static GetCinemaDto fromCinemaToGetCinemaDto(Cinema cinema) {
        return GetCinemaDto.builder()
                .city(cinema.getCity())
                .id(cinema.getId())
                .addressLine(cinema.getAddressLine())
                .name(cinema.getName())
                .roomNumbers(cinema.getRoomNumbers())
                .build();
    }

    static User fromCreateUserToUser(CreateUserDto createUserDto) {
        return User.builder()
                .username(createUserDto.getUsername())
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .role(createUserDto.getRole())
                .build();
    }

    static GetUserDto fromUserToGetUserDto(User user) {
        return GetUserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .id(user.getId())
                .role(user.getRole())
                .build();
    }

    static MovieShowing fromCreateMovieShowingToMovieShowing(CreateMovieShowingDto createMovieShowingDto) {
        return MovieShowing.builder()
                .movieShowingTime(createMovieShowingDto.getMovieShowingTime())
                .roomNumber(createMovieShowingDto.getRoomNumber())
                .build();
    }

    static GetMovieShowingDto fromMovieShowingToGetMovieShowingDto(MovieShowing movieShowing) {
        return GetMovieShowingDto.builder()
                .cinemaId(movieShowing.getCinema().getId())
                .id(movieShowing.getId())
                .movieId(movieShowing.getMovie().getId())
                .movieShowingTime(movieShowing.getMovieShowingTime())
                .roomNumber(movieShowing.getRoomNumber())
                .build();
    }

    static GetOrderDto fromOrderToGetOrderDto(Order order) {
        return GetOrderDto
                .builder()
                .orderTime(order.getOrderTime())
                .totalPrice(order.getTotalPrice())
                .userID(order.getUser().getId())
                .ticketsIDs(order
                        .getTickets()
                        .stream()
                        .map(BaseEntity::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
