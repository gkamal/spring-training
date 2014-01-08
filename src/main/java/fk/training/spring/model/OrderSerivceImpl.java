package fk.training.spring.model;

import java.math.BigDecimal;

public class OrderSerivceImpl implements OrderService {

    private final ProductRepository productRepostiory;

    public OrderSerivceImpl(ProductRepository productRepostiory) {
        this.productRepostiory = productRepostiory;
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

    }
}
