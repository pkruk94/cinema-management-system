package order;

import base.generic.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
}
