package fk.training.spring.internal;

import fk.training.spring.OrderService;
import fk.training.spring.model.LineItem;
import fk.training.spring.model.Order;
import fk.training.spring.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class OrderSerivceImplTest {

    OrderService orderService;

    @Before
    public void setup() {

    }

    @Test
    public void testComputeCost() throws Exception {
        orderService = new OrderSerivceImpl(new ProductRepository() {
            @Override
            public Product findById(Long productId) {
                Product product = new Product();
                product.setPrice(new BigDecimal("100"));
                return product;
            }

            @Override
            public void update(Product p) {

            }
        }, null);

        Order order = new Order();
        LineItem lineItem = new LineItem();
        lineItem.setProductId(100L);
        lineItem.setQuantity(1);
        order.addItem(lineItem);

        assertEquals(new BigDecimal("100"),orderService.computeCost(order));

    }
}
