package tenx.store.internal;

import tenx.store.model.Order;


public interface OrderDao {
	
	Long createOrder(Order order);

}
