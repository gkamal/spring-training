package fk.training.spring.model;

import javax.sql.DataSource;
import java.math.BigDecimal;

public class JdbcProductRepository implements ProductRepository {

    private final DataSource dataSource;

    public JdbcProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Product findById(Long productId) {
        Product product = new Product();
        product.setPrice(new BigDecimal("100"));
        return product;
    }
}
