package order;

import base.generic.CrudRepository;
import value_object.Money;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    List<Order> findAllByUserIdAndData(Long userId, Money minValue, Money maxValue, LocalDateTime minTime, LocalDateTime maxTime);
}
