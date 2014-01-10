package fk.training.spring.internal;

import fk.training.spring.OrderService;
import fk.training.spring.model.LineItem;
import fk.training.spring.model.Order;
import fk.training.spring.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml",
        "classpath:test-infra-config.xml"})
public class OrderServiceImplIntegrationTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testComputeCost() {
        assertNotNull(orderService);
        Order order = new Order();
        LineItem lineItem = new LineItem();
        lineItem.setProductId(1L);
        lineItem.setQuantity(1);
        order.addItem(lineItem);

        assertEquals(new BigDecimal("400"), orderService.computeCost(order));
    }


    @Test
    public void testCreateOrder() {
        Order o = new Order();
        LineItem item = new LineItem();
        item.setProductId(1l);
        item.setQuantity(5);
        o.addItem(item);

        item = new LineItem();
        item.setProductId(2l);
        item.setQuantity(25);
        o.addItem(item);

        try {
            orderService.processOrder(o);
            fail("should result in exception");
        } catch(RuntimeException e) {
            System.out.println(e);
            // ingore
        }

        Product p = productRepository.findById(1l);

        assertEquals(10, p.getAvailableQuantity());
    }

}
