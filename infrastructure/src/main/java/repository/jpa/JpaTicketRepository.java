package repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ticket.Ticket;

import java.util.List;

public interface JpaTicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByMovieShowingId(Long id);
}
