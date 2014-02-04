package fk.training.spring.internal;

import fk.training.spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcProductRepository extends JdbcBaseRepository implements ProductRepository {

    @Autowired
    public JdbcProductRepository(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public Product findById(Long productId) {
        return query("select * from products where id = ?", new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs) throws SQLException {
               Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setAvailableQuantity(rs.getInt("available_quantity"));
                p.setPrice(rs.getBigDecimal("price"));
                return p;
            }
        }, productId);
    }

    interface RowMapper<T> {
        T mapRow(ResultSet rs) throws SQLException;
    }

    @Override
    public void update(Product p) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DatasourceUtil.getConnection();
            ps = connection.prepareStatement("update products set available_quantity = ?  where id = ?");
            ps.setInt(1, p.getAvailableQuantity());
            ps.setLong(2, p.getId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated != 1) {
                throw new RuntimeException("Update product failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(rs);
            closeSilently(ps);
        }
    }

}
