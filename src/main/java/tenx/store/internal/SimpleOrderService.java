package tenx.store.internal;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tenx.store.OrderService;
import tenx.store.infra.Profile;
import tenx.store.model.LineItem;
import tenx.store.model.Order;
import tenx.store.model.Product;

@Service
//@Component
//@Scope("prototype")
public class SimpleOrderService implements OrderService {
	private ProductDao productDao;
//	private int maxQuantity = 10;
	
	@Autowired
	public SimpleOrderService(/*@Qualifier("jdbcProductDao")*/ ProductDao productDao) {
		super();
		this.productDao = productDao;
	}
	
	@PostConstruct
	public void init() {
		
	}
	
	@PreDestroy
	public void destroy() {
		
	}
	

	@Profile
	public BigDecimal calculateCost(Order order) {
		
		BigDecimal cost = new BigDecimal("0");
		for(LineItem lineItem:order.getItems()) {
			Product p = productDao.findById(lineItem.getProductId());
			cost = cost.add(p.getPrice().multiply(new BigDecimal(lineItem.getQuantity())));
		}
		return cost;
		
		
	}

	public Long processOrder(Order order) {
		// security 
		// start trans
		// log
		// profiling
		
		// TODO Auto-generated method stub
		return null;
	
	}

	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

//	@Value("${orderService.maxQuanity}")
//	public void setMaxQuantity(int maxQuantity) {
//		this.maxQuantity = maxQuantity;
//	}

//	public void setProductDao(ProductDao productDao) {
//		this.productDao = productDao;
//	}
	
	
	

}
