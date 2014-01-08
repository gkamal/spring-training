package fk.training.spring.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class OrderServiceImplIntegrationTest {

    private OrderService orderService;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml",
                "test-infra-config.xml");
        orderService = (OrderService) applicationContext.getBean("orderService");
    }

    @Test
    public void testComputeCost() {
        assertNotNull(orderService);
        Order order = new Order();
        LineItem lineItem = new LineItem();
        lineItem.setProductId(100L);
        lineItem.setQuantity(1);
        order.addItem(lineItem);

        assertEquals(new BigDecimal("100"), orderService.computeCost(order));
    }


}
