package fk.training.spring.internal;

import fk.training.spring.model.Order;

public interface OrderRepository {
    void createOrder(Order order);
}
