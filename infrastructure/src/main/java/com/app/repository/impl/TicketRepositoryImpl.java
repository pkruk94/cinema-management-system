package com.app.repository.impl;

import com.app.repository.jpa.JpaTicketRepository;
import com.app.ticket.Ticket;
import com.app.ticket.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final JpaTicketRepository jpaTicketRepository;

    @Override
    public Optional<Ticket> addOrUpdate(Ticket ticket) {
        return Optional.of(jpaTicketRepository.save(ticket));
    }

    @Override
    public List<Ticket> addOrUpdateMany(List<Ticket> items) {
        return jpaTicketRepository.saveAll(items);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return jpaTicketRepository.findById(id);
    }

    @Override
    public List<Ticket> findAll() {
        return jpaTicketRepository.findAll();
    }

    @Override
    public List<Ticket> findAllById(List<Long> ids) {
        return jpaTicketRepository.findAllById(ids);
    }

    @Override
    public Optional<Ticket> deleteById(Long id) {
        return jpaTicketRepository
                .findById(id)
                .flatMap(ticket -> {
                    jpaTicketRepository.deleteById(id);
                    return Optional.of(ticket);
                });
    }

    @Override
    public List<Ticket> deleteAllById(List<Long> ids) {
        List<Ticket> tickets = jpaTicketRepository.findAllById(ids);
        jpaTicketRepository.deleteAll(tickets);
        return tickets;
    }

    @Override
    public boolean deleteAll() {
        jpaTicketRepository.deleteAll();
        return true;
    }

    @Override
    public List<Ticket> findAllByMovieShowingId(Long id) {
        return jpaTicketRepository.findAllByMovieShowingId(id);
    }
}
