package fk.training.spring.model;

import java.math.BigDecimal;

public interface OrderService {

    public BigDecimal computeCost(Order order);

    public void processOrder(Order order);

}
