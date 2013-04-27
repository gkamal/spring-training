package tenx.store.internal;

import java.util.List;

import tenx.store.model.Product;

public interface ProductDao {
	List<Product> search(String name);
	Product findById(Long id);
	Product update(Product product);
}
