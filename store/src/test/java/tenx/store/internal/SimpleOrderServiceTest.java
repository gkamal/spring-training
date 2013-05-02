package tenx.store.internal;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import tenx.store.OrderService;
import tenx.store.infra.LoggingWrapper;
import tenx.store.model.LineItem;
import tenx.store.model.Order;
import tenx.store.model.Product;

public class SimpleOrderServiceTest {
	OrderService orderService;

	@Before
	public void setup() {
		orderService = new SimpleOrderService(null);
		orderService = (OrderService) LoggingWrapper.wrap(orderService);
	}

	@Test
	public void testCalculateCost() {
		Order o = new Order();
		LineItem item = new LineItem();
		Product p = new Product();
		p.setId(1l);
		p.setPrice(new BigDecimal(100));
		item.setProduct(p);
		item.setQuantity(5);
		o.addItem(item);

		assertEquals(new BigDecimal(500), orderService.calculateCost(o));
	}

}
