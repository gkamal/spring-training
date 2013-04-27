package tenx.store.internal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tenx.store.OrderService;
import tenx.store.infra.Profile;
import tenx.store.model.LineItem;
import tenx.store.model.Order;
import tenx.store.model.Product;

@Service
//@Component
//@Scope("prototype")
@Transactional
public class SimpleOrderService implements OrderService, ApplicationContextAware {
	private ProductDao productDao;
	private OrderDao  orderDao;
	
	
//	private int maxQuantity = 10;
	
//	@Autowired
	private OrderService proxyThis;
	
	@Autowired
	public SimpleOrderService(/*@Qualifier("jdbcProductDao")*/ ProductDao productDao,
			OrderDao orderDao) {
		super();
		this.productDao = productDao;
		this.orderDao = orderDao;
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
	
	@Transactional
	public List<Long> bulkOrder(List<Order> orders) {

		for(Order o:orders) {
			processOrder(o);
		}
		
		return new ArrayList<Long>();
	}

	@Transactional
	public Long processOrder(Order order)  {
		proxyThis.createAudit("User x tried to create order");
		//DataSourceUtils.doGetConnection(dataSource)
		// conn
		// 
		// userTransaction.begin()
		// 
		// conn
		// conn.autoComitt(false)
		// conn2.autoCommit(flase)
		//try{
		
//		try {
		for(LineItem lineItem:order.getItems()) {
			Product p = productDao.findById(lineItem.getProductId());
			if (p.getAvailableQuantity() < lineItem.getQuantity()) {
				throw new RuntimeException("Insufficient quantity");
			}
			p.setAvailableQuantity(p.getAvailableQuantity() - lineItem.getQuantity());
			productDao.update(p);
		}
//		} catch(RuntimeException e) {
//			//
//		}
		
		return orderDao.createOrder(order);
		// conn.commit
		// conn2.commit
		// userTrancation.commit()
		// catch()
		//  conn.rollback
	}

	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void createAudit(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		proxyThis = applicationContext.getBean(OrderService.class);
	}

//	@Value("${orderService.maxQuanity}")
//	public void setMaxQuantity(int maxQuantity) {
//		this.maxQuantity = maxQuantity;
//	}

//	public void setProductDao(ProductDao productDao) {
//		this.productDao = productDao;
//	}
	
	
	

}
