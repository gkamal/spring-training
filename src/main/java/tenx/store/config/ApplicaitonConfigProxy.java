package tenx.store.config;

import tenx.store.OrderService;
import tenx.store.internal.ProductDao;

public class ApplicaitonConfigProxy extends ApplicaitonConfig {

//	@Override
//	public OrderService orderService2() {
//		// TODO Auto-generated method stub
//		return super.orderService2();
//	}

	@Override
	public OrderService orderService() {
		// TODO Auto-generated method stub
		return super.orderService();
	}

	@Override
	public ProductDao productDao() {
		// TODO Auto-generated method stub
		return super.productDao();
	}

	
	
}
