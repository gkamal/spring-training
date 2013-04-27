package tenx.store.internal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import tenx.store.model.Product;

//@Repository
//@Component
//@Lazy
public class InMemoryProductDao implements ProductDao {

	Map<Long, Product> products = new HashMap<Long, Product>(); 
	
	public InMemoryProductDao() {
		Product p = new Product();
		p.setId(1L);
		p.setName("Effective Java");
		p.setPrice(new  BigDecimal("399"));
		p.setAvailableQuantity(100);
		products.put(p.getId(), p);
	}
	
	
	public Product findById(Long id) {
		return products.get(id);
	}

	public Product update(Product product) {
		products.put(product.getId(), product);
		return product;
	}


	@Override
	public List<Product> search(String name) {
		// TODO Auto-generated method stub
		return new ArrayList<Product>();
	}
	
	
	
	

}
