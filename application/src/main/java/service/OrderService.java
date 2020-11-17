package service;

import discount.DiscountRepository;
import dto.order.CreateOrderDto;
import dto.order.GetOrderDto;
import dto.ticket.CreateTicketDto;
import exception.OrderServiceException;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;
import movie_showing.MovieShowing;
import movie_showing.MovieShowingRepository;
import order.Order;
import order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticket.Ticket;
import ticket.TicketRepository;
import user.User;
import user.UserRepository;
import validation.order.CreateOrderValidator;
import validation.ticket.CreateTicketValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;
    private final MovieShowingRepository movieShowingRepository;
    private final DiscountRepository discountRepository;
    private final UserRepository userRepository;

    public Long createOrder(CreateOrderDto createOrderDto) {
        var createOrderValidator = new CreateOrderValidator();
        var createTicketValidator = new CreateTicketValidator();

        validateCreateOrderDto(createOrderDto, createOrderValidator);
        validateCreateTicketsDtos(createOrderDto, createTicketValidator);
        validateForBookedTickets(createOrderDto);

        MovieShowing movieShowing = movieShowingRepository
                .findById(createOrderDto
                        .getCreateTicketDtos()
                        .get(0).getMovieShowingId())
                .orElseThrow(() -> new OrderServiceException("Movie showing could not be found"));

        User user = userRepository.findById(createOrderDto.getUserId())
                .orElseThrow(() -> new OrderServiceException("User could not be found in database"));

        Order newOrder = orderRepository.addOrUpdate(Order
                .builder()
                .orderTime(LocalDateTime.now())
                .user(user)
                .build())
                .orElseThrow(() -> new OrderServiceException("Order could not be added"));

        var newTickets = ticketRepository
                .addOrUpdateMany(
                        createOrderDto
                                .getCreateTicketDtos()
                                .stream()
                                .map(createTicketDto -> {
                                    var discount = discountRepository
                                            .findById(createTicketDto.getDiscountId())
                                            .orElseThrow(() -> new OrderServiceException("Discount does not exist in DB"));

                                    return Ticket.builder()
                                            .discount(discount)
                                            .movieShowing(movieShowing)
                                            .seatNumber(createTicketDto.getSeatNumber())
                                            .order(newOrder)
                                            .build();
                                })
                                .collect(Collectors.toList()));

        newOrder.setTickets(newTickets);
        orderRepository.addOrUpdate(newOrder);
        // TODO send email
        return newOrder.getId();
    }

    public List<GetOrderDto> getAllOrdersForUser(Long userId) {
        if (userId == null) {
            throw new OrderServiceException("User id cannot be null");
        }

        return orderRepository
                .findAllByUserId(userId)
                .stream()
                .map(Mapper::fromOrderToGetOrderDto)
                .collect(Collectors.toList());
    }

    private void validateForBookedTickets(CreateOrderDto createOrderDto) {
        List<Ticket> tickets = ticketRepository
                .findAllByMovieShowingId(createOrderDto.getCreateTicketDtos()
                        .get(0)
                        .getMovieShowingId());

        if (tickets
                .stream()
                .map(Ticket::getSeatNumber)
                .collect(Collectors.toList())
                .retainAll(createOrderDto
                        .getCreateTicketDtos()
                        .stream()
                        .map(CreateTicketDto::getSeatNumber)
                        .collect(Collectors.toList()))
        ) {
            throw new OrderServiceException("Some tickets are already taken");
        }
    }

    private void validateCreateTicketsDtos(CreateOrderDto createOrderDto, CreateTicketValidator createTicketValidator) {
        for (CreateTicketDto createTicketDto : createOrderDto.getCreateTicketDtos()) {
            var ticketErrors = createTicketValidator.validate(createTicketDto);
            if (!ticketErrors.isEmpty()) {
                var errorMsg = ticketErrors
                        .entrySet()
                        .stream()
                        .map(e -> e.getKey() + ": " + e.getValue())
                        .collect(Collectors.joining(", "));

                throw new OrderServiceException(errorMsg);
            }
        }
    }

    private void validateCreateOrderDto(CreateOrderDto createOrderDto, CreateOrderValidator createOrderValidator) {
        var orderErrors = createOrderValidator.validate(createOrderDto);
        if (!orderErrors.isEmpty()) {
            var errorMsg = orderErrors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));

            throw new OrderServiceException(errorMsg);
        }
    }
}
