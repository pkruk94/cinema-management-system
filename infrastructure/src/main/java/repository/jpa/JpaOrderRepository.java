package repository.jpa;

import order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserId(Long userId);
}
