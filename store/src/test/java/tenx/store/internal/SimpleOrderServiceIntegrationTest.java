package tenx.store.internal;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tenx.store.OrderService;
import tenx.store.model.LineItem;
import tenx.store.model.Order;
import tenx.store.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application-config.xml", "classpath:test-infra-config.xml"})
@Transactional
public class SimpleOrderServiceIntegrationTest {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductDao productDao;
		
	@Test
	public void testCacluateCost() {
		Order o = new Order();
		LineItem item = new LineItem();
		item.setProduct(productDao.findById(1l));
		item.setQuantity(5);
		o.addItem(item);
		
		assertEquals(2000d, orderService.calculateCost(o).doubleValue(),0.001);
		
		orderService.processOrder(o);
		
		Product p = productDao.findById(1l);
		
		assertEquals(5, p.getAvailableQuantity());
	}
	
	@Test
	public void testCreateOrder() {
		Order o = new Order();
		LineItem item = new LineItem();
		item.setProduct(productDao.findById(1l));
		item.setQuantity(5);
		o.addItem(item);
		
		item = new LineItem();
		item.setProduct(productDao.findById(2l));
		item.setQuantity(25);
		o.addItem(item);
		
		try {
			orderService.processOrder(o);
			fail("should result in exception");
		} catch(RuntimeException e) {
			// ingore
		}
		
		Product p = productDao.findById(1l);
		
		//assertEquals(10, p.getAvailableQuantity());
	}
	

}
