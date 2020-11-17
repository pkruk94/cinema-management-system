package service;

import discount.DiscountRepository;
import lombok.RequiredArgsConstructor;
import movie_showing.MovieShowingRepository;
import order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticket.TicketRepository;
import user.UserRepository;

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
