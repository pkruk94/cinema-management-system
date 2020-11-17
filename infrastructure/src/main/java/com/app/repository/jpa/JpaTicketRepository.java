package com.app.repository.jpa;

import com.app.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByMovieShowingId(Long id);
}
