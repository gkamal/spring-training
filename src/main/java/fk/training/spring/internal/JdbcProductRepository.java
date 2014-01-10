package fk.training.spring.internal;

import fk.training.spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
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
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("select * from products where id = ?");
            ps.setLong(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
               Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setAvailableQuantity(rs.getInt("available_quantity"));
                p.setPrice(rs.getBigDecimal("price"));
                return p;
            }  else {
                return null;
            }
        } catch (SQLException e) {
           throw new RuntimeException(e);
        } finally {
            closeSilently(rs);
            closeSilently(ps);
            closeSilently(connection);
        }
    }

    @Override
    public void update(Product p) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
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
            closeSilently(connection);
        }
    }

}
