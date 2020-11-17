package com.app.order;

import com.app.base.generic.CrudRepository;
import com.app.value_object.Money;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    List<Order> findAllByUserIdAndData(Long userId, Money minValue, Money maxValue, LocalDateTime minTime, LocalDateTime maxTime);
}
