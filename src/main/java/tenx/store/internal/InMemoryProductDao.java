package tenx.store.internal;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


import tenx.store.model.Product;

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

	public void update(Product product) {
		products.put(product.getId(), product);
	}

}
