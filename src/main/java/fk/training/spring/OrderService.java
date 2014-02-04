package fk.training.spring;

import fk.training.spring.model.Order;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface OrderService {

    public BigDecimal computeCost(Order order);

    public void processOrder(Order order) throws Exception;

}
