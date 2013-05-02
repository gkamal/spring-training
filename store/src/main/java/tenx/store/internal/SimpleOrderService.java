package tenx.store.internal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tenx.store.OrderService;
import tenx.store.infra.Profile;
import tenx.store.model.LineItem;
import tenx.store.model.Order;
import tenx.store.model.Product;

@Service
@Transactional
public class SimpleOrderService implements OrderService {
	private OrderDao orderDao;

	@Autowired
	public SimpleOrderService(
			OrderDao orderDao) {
		super();
		this.orderDao = orderDao;
	}

	@Profile
	public BigDecimal calculateCost(Order order) {
		return order.getCost();
	}

	@Transactional(readOnly = true)
	public List<Long> bulkOrder(List<Order> orders) {
		for (Order o : orders) {
			processOrder(o);
		}
		return new ArrayList<Long>();
	}

	@Transactional
	public Long processOrder(Order order) {
		getProxyOrThis().createAudit("User x tried to create order");
		for (LineItem lineItem : order.getItems()) {
			Product p = lineItem.getProduct();
			if (p.getAvailableQuantity() < lineItem.getQuantity()) {
				throw new RuntimeException("Insufficient quantity");
			}
			p.setAvailableQuantity(p.getAvailableQuantity()
					- lineItem.getQuantity());
		}
		return orderDao.createOrder(order);
	}

	// Retreive the proxy - to ensure
	// that calls to methods of the same
	// class go through the proxy
	// should enable expose-proxy attribute on aop:aspectj-proxy tag
	private OrderService getProxyOrThis() {
		Object proxy = AopContext.currentProxy();
		if (proxy != null && proxy instanceof OrderService) {
			return (OrderService) proxy;
		} else {
			return this;
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void createAudit(String string) {
		// TODO Auto-generated method stub

	}

	protected void test() {

	}

}
