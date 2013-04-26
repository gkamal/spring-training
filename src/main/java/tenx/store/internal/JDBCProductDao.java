package tenx.store.internal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tenx.store.model.Product;

@Repository
public class JDBCProductDao implements ProductDao {

	private final class ProductRowmapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum)
				throws SQLException {
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
	public JDBCProductDao(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Product findById(Long id) {
		return jdbcTemplate.queryForObject("select * from products where id = ?",
				new ProductRowmapper(), id);
	}	
		
	@Override
	public void update(Product product) {
		//jdbcTemplate.queryForInt(sql)
		jdbcTemplate.update("update products set name=?, price=?, available_quantity=? where id = ?",
				product.getName(),
				product.getPrice(),
				product.getAvailableQuantity(),
				product.getId());
	}

}

//query("select * from products where id = ?", id, new Rowmapper(){
//@Override
//public Object mapRow(ResultSet rs) throws SQLException {
//	Product p = new Product();
//	p.setId(rs.getLong("id"));
//	p.setName(rs.getString("name"));
//	p.setPrice(rs.getBigDecimal("price", 2));
//	p.setAvailableQuantity(rs.getInt("available_quantity"));
//	return p;
//}
//});

//return null;

//interface Rowmapper<T> {
//T mapRow(ResultSet rs) throws SQLException;
//}
//
//private <T> T query(String query,Long id, Rowmapper<T> rowmapper) {
//Connection connection = null;
//try {
//connection = dataSource.getConnection();
//PreparedStatement ps = connection.prepareStatement(query);
//ps.setLong(1, id);
//ResultSet rs = ps.executeQuery();
//if (rs.next()) {
//	return rowmapper.mapRow(rs);
//}
//} catch (SQLException e) {
//// TODO: handle exception
//
//} finally {
//// closed
//try{if (connection != null) connection.close();}catch (SQLException e){};
//}
//return null;
//}

	
	