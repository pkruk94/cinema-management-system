package com.app.service;

import com.app.discount.DiscountRepository;
import com.app.dto.order.CreateOrderDto;
import com.app.dto.order.GetOrderDto;
import com.app.dto.order.OrderFilterData;
import com.app.dto.ticket.CreateTicketDto;
import com.app.exception.OrderServiceException;
import com.app.mapper.Mapper;
import com.app.movie_showing.MovieShowing;
import com.app.movie_showing.MovieShowingRepository;
import com.app.order.Order;
import com.app.order.OrderRepository;
import com.app.ticket.Ticket;
import com.app.ticket.TicketRepository;
import com.app.user.User;
import com.app.user.UserRepository;
import com.app.validation.order.CreateOrderValidator;
import com.app.validation.ticket.CreateTicketValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<GetOrderDto> getAllOrderForUserWithFilter(Long userId, OrderFilterData orderFilterData) {
        if (userId == null) {
            throw new OrderServiceException("User id cannot be null");
        }

        if (orderFilterData == null) {
            throw new OrderServiceException("Filter data cannot be null");
        }

        return orderRepository
                .findAllByUserIdAndData(userId, orderFilterData.getMinValue(), orderFilterData.getMaxValue(), orderFilterData.getMinTime(), orderFilterData.getMaxTime())
                .stream()
                .map(Mapper::fromOrderToGetOrderDto)
                .collect(Collectors.toList());
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
