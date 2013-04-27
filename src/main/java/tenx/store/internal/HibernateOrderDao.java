package tenx.store.internal;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tenx.store.model.Order;

@Repository
public class HibernateOrderDao implements OrderDao {

	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public Long createOrder(Order order) {
		sessionFactory.getCurrentSession().persist(order);
		return order.getId();
	}
	
	

}
