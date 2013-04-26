package tenx.store.internal;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tenx.store.OrderService;
import tenx.store.model.LineItem;
import tenx.store.model.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application-config.xml", "classpath:test-infra-config.xml"})
//@ContextConfiguration(classes=ApplicaitonConfig.class)
public class SimpleOrderServiceIntegrationTest {

	@Autowired
	private OrderService orderService;
	
	@Before
	public void setup() {
//		ApplicationContext applicationContext 
//			= new ClassPathXmlApplicationContext("file:application-config.xml");
//		orderService = applicationContext.getBean("orderService", OrderService.class);
	}

	@Test
	public void testCacluateCost() {
		Order o = new Order();
		LineItem item = new LineItem();
		item.setProductId(1l);
		item.setQuantity(5);
		o.addItem(item);
		
		assertEquals(2000d, orderService.calculateCost(o).doubleValue(),0.001);
	}

}
