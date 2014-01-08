package fk.training.spring.model;

/**
 * Created by kamal.govindraj on 08/01/14.
 */
public interface ProductRepository {
    Product findById(Long productId);
}
