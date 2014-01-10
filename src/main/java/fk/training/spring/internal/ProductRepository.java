package fk.training.spring.internal;

import fk.training.spring.model.Product;

public interface ProductRepository {
    Product findById(Long productId);

    void update(Product p);
}
