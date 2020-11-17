package com.app.repository.jpa;

import com.app.order.Order;
import com.app.value_object.Money;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserId(Long userId);

    List<Order> findAllByUserIdAndTotalPriceBetweenAndOrderTimeBetween(Long userId, Money minValue, Money maxValue, LocalDateTime minTime, LocalDateTime maxTime);
}
