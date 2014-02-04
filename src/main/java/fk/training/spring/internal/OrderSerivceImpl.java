package fk.training.spring.internal;

import fk.training.spring.OrderService;
import fk.training.spring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

@Service("orderServiceTarget")
public class OrderSerivceImpl implements OrderService {

    private final ProductRepository productRepostiory;
    private final OrderRepository orderRepository;
    private final DataSource dataSource;

    @Autowired
    public OrderSerivceImpl(ProductRepository productRepostiory, OrderRepository orderRepository, DataSource dataSource) {
        this.productRepostiory = productRepostiory;
        this.orderRepository = orderRepository;
        this.dataSource = dataSource;
    }

    @Override
    public BigDecimal computeCost(Order order) {
        BigDecimal total = new BigDecimal("0");
        for(LineItem item : order.getItems()) {
           Product product = productRepostiory.findById(item.getProductId());
           total = total.add(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return total;
    }

    @Override
    public void processOrder(Order order) throws Exception {
        for(LineItem lineItem:order.getItems()) {
            Product p = productRepostiory.findById(lineItem.getProductId());
            if (p.getAvailableQuantity() < lineItem.getQuantity()) {
                throw new RuntimeException("Insufficient quantity");
            }
            p.setAvailableQuantity(p.getAvailableQuantity() - lineItem.getQuantity());
            productRepostiory.update(p);
        }
        orderRepository.createOrder(order);
    }
}
