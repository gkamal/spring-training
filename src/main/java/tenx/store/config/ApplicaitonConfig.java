package tenx.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tenx.store.OrderService;
import tenx.store.internal.InMemoryProductDao;
import tenx.store.internal.ProductDao;
import tenx.store.internal.SimpleOrderService;

//@Configuration
public class ApplicaitonConfig {

//	@Bean
//	public OrderService orderService2() {
//		SimpleOrderService orderService = new SimpleOrderService(productDao());
//		return orderService;
//	}

	@Bean
	public OrderService orderService() {
		SimpleOrderService orderService = new SimpleOrderService(productDao());
		return orderService;
	}
	
	
	@Bean
	public ProductDao productDao() {
		return new InMemoryProductDao();
	}

}
