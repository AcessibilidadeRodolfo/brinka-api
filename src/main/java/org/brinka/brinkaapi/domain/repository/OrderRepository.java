package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.Order;
import org.brinka.brinkaapi.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findByIdAndUser(Integer id, User user);
    List<Order> findAllByUser(User user);
}
