package tenx.store.internal;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tenx.store.model.Product;

@Repository
public class HibernateProductDao implements ProductDao {

	private SessionFactory sessionFactory;
	
	@Autowired
	public HibernateProductDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory; 
	}
	
	
	@Override
	public Product findById(Long id) {
		return (Product) getSession().get(Product.class, id);
	}


	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Product update(Product product) {
		return (Product) getSession().merge(product);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Product> search(String name) {
		return getSession()
			.createQuery("select p from Product p where p.name like :name")
			.setString("name", name + "%")
			//.uniqueResult()
			.list();
	}

	
	
}







