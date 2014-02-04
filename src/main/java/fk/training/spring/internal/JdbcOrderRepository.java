package fk.training.spring.internal;

import fk.training.spring.model.LineItem;
import fk.training.spring.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcOrderRepository extends JdbcBaseRepository implements OrderRepository {

    @Autowired
    public JdbcOrderRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void createOrder(Order order) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DatasourceUtil.getConnection();
            ps = connection.prepareStatement("insert into orders (id,cost) " +
                    "values(?,?)");
            ps.setLong(1, order.getId());
            ps.setBigDecimal(2, order.getCost());
            ps.executeUpdate();

            for(LineItem lineItem:order.getItems()) {
                ps = connection.prepareStatement("insert into line_items (product_id, quantity, order_id) " +
                        "values(?,?,?)");
                ps.setLong(1, lineItem.getProductId());
                ps.setInt(2, lineItem.getQuantity());
                ps.setLong(3, order.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(rs);
            closeSilently(ps);
        }
    }
}
