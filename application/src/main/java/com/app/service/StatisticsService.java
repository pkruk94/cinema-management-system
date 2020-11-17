package com.app.service;

import com.app.discount.DiscountRepository;
import com.app.movie_showing.MovieShowingRepository;
import com.app.order.OrderRepository;
import com.app.ticket.TicketRepository;
import com.app.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsService {

    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;
    private final MovieShowingRepository movieShowingRepository;
    private final DiscountRepository discountRepository;
    private final UserRepository userRepository;

    public String findCityWithHighestNumberOfCustomers() {
        return null;
    }
}
