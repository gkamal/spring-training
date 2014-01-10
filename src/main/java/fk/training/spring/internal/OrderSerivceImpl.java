package fk.training.spring.internal;

import fk.training.spring.OrderService;
import fk.training.spring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderSerivceImpl implements OrderService {

    private final ProductRepository productRepostiory;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderSerivceImpl(ProductRepository productRepostiory, OrderRepository orderRepository) {
        this.productRepostiory = productRepostiory;
        this.orderRepository = orderRepository;
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
    public void processOrder(Order order) {
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
