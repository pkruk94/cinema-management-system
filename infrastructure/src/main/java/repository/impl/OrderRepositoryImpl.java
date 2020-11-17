package repository.impl;

import lombok.RequiredArgsConstructor;
import order.Order;
import order.OrderRepository;
import org.springframework.stereotype.Repository;
import repository.jpa.JpaOrderRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public List<Order> findAllByUserId(Long userId) {
        return jpaOrderRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<Order> addOrUpdate(Order order) {
        return Optional.of(jpaOrderRepository.save(order));
    }

    @Override
    public List<Order> addOrUpdateMany(List<Order> items) {
        return jpaOrderRepository.saveAll(items);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpaOrderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return jpaOrderRepository.findAll();
    }

    @Override
    public List<Order> findAllById(List<Long> ids) {
        return jpaOrderRepository.findAllById(ids);
    }

    @Override
    public Optional<Order> deleteById(Long id) {
        return jpaOrderRepository
                .findById(id)
                .flatMap(order -> {
                    jpaOrderRepository.deleteById(id);
                    return Optional.of(order);
                });
    }

    @Override
    public List<Order> deleteAllById(List<Long> ids) {
        List<Order> orders = jpaOrderRepository.findAllById(ids);
        jpaOrderRepository.deleteAll(orders);
        return orders;
    }

    @Override
    public boolean deleteAll() {
        jpaOrderRepository.deleteAll();
        return true;
    }
}
