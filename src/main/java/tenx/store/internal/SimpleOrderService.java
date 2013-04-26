package tenx.store.internal;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tenx.store.OrderService;
import tenx.store.model.LineItem;
import tenx.store.model.Order;
import tenx.store.model.Product;

@Component
public class SimpleOrderService implements OrderService {
	private ProductDao productDao;
	
	@Autowired
	public SimpleOrderService(/*@Qualifier("jdbcProductDao")*/ ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	public BigDecimal calculateCost(Order order) {
		BigDecimal cost = new BigDecimal("0");
		for(LineItem lineItem:order.getItems()) {
			Product p = productDao.findById(lineItem.getProductId());
			cost = cost.add(p.getPrice().multiply(new BigDecimal(lineItem.getQuantity())));
		}
		return cost;
	}

	public Long processOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

//	public void setProductDao(ProductDao productDao) {
//		this.productDao = productDao;
//	}
	
	

}
