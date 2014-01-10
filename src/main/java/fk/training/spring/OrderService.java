package fk.training.spring;

import fk.training.spring.model.Order;

import java.math.BigDecimal;

public interface OrderService {

    public BigDecimal computeCost(Order order);

    public void processOrder(Order order);

}
