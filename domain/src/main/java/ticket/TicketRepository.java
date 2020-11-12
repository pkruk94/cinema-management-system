package ticket;

import base.generic.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findAllByMovieShowingId(Long id);
}
