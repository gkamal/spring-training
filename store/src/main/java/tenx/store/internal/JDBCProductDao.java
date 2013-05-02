package tenx.store.internal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import tenx.store.model.Product;

//@Repository
public class JDBCProductDao implements ProductDao {

	private final class ProductRowmapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product p = new Product();
			p.setId(rs.getLong("id"));
			p.setName(rs.getString("name"));
			p.setPrice(rs.getBigDecimal("price", 2));
			p.setAvailableQuantity(rs.getInt("available_quantity"));
			return p;
		}
	}

	DataSource dataSource;
	JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCProductDao(/* @Qualifier("inventoryDs") */DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Product findById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from products where id = ?", new ProductRowmapper(),
				id);
	}

	@Override
	public Product update(Product product) {
		// jdbcTemplate.queryForInt(sql)
		jdbcTemplate
				.update("update products set name=?, price=?, available_quantity=? where id = ?",
						product.getName(), product.getPrice(),
						product.getAvailableQuantity(), product.getId());
		return product;
	}

	@Override
	public List<Product> search(String name) {
		return new ArrayList<Product>();
	}

	@Override
	public void create(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
