package tenx.store.internal;

import tenx.store.model.Product;

public interface ProductDao {
	Product findById(Long id);
	void update(Product product);
}
