package tenx.store;

import java.math.BigDecimal;

import tenx.store.model.Order;

public interface OrderService {
	
	BigDecimal calculateCost(Order order);
	
	Long processOrder(Order order);

}
