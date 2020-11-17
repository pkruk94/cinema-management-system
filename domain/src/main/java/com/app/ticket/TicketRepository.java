package com.app.ticket;

import com.app.base.generic.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findAllByMovieShowingId(Long id);
}
