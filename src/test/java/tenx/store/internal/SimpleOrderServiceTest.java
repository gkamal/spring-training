package tenx.store.internal;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import tenx.store.OrderService;
import tenx.store.infra.LoggingWrapper;
import tenx.store.model.LineItem;
import tenx.store.model.Order;
import tenx.store.model.Product;

public class SimpleOrderServiceTest {
//	OrderService orderService;
//	@Before
//	public void setup() {
//	ProductDao productDao = new ProductDao() {
//			public void update(Product product) {
//			}
//			
//			public Product findById(Long id) {
//				Product p = new Product();
//				p.setPrice(new BigDecimal(100));
//				return p;
//			}
//		};
//		orderService = new SimpleOrderService(productDao,null);
//		orderService = (OrderService) LoggingWrapper.wrap(orderService);
//		//orderService.setProductDao(productDao);
//	}
//	@Test
//	public void testCalculateCost() {
//		Order o = new Order();
//		LineItem item = new LineItem();
//		item.setProductId(1l);
//		item.setQuantity(5);
//		o.addItem(item);
//		
//		assertEquals(new BigDecimal(500), orderService.calculateCost(o));
//	}

}
