package com.app.controller;

import com.app.dto.order.CreateOrderDto;
import com.app.dto.order.GetOrderDto;
import com.app.dto.order.OrderFilterData;
import com.app.dto.response.ResponseData;
import com.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Long> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        return ResponseData.<Long>builder()
                .data(orderService.createOrder(createOrderDto))
                .build();
    }

    @GetMapping("/{userId}")
    public ResponseData<List<GetOrderDto>> getAllOrdersForUser(@PathVariable Long userId) {
        return ResponseData.<List<GetOrderDto>>builder()
                .data(orderService.getAllOrdersForUser(userId))
                .build();
    }

    @GetMapping("/filter/{userId}")
    public ResponseData<List<GetOrderDto>> getAllOrdersWithFilter(@RequestBody OrderFilterData orderFilterData, @PathVariable Long userId) {
        return ResponseData.<List<GetOrderDto>>builder()
                .data(orderService.getAllOrderForUserWithFilter(userId, orderFilterData))
                .build();
    }
}
