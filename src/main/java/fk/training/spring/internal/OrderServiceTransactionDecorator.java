package fk.training.spring.internal;

import fk.training.spring.OrderService;
import fk.training.spring.model.LineItem;
import fk.training.spring.model.Order;
import fk.training.spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;

@Component("orderService")
public class OrderServiceTransactionDecorator implements OrderService {

    private final DataSource dataSource;
    private final OrderService target;

    @Autowired
    public OrderServiceTransactionDecorator(DataSource dataSource,
                                            @Qualifier("orderServiceTarget") OrderService target) {
        this.dataSource = dataSource;
        this.target = target;
    }

    @Override
    public BigDecimal computeCost(Order order) {
        return target.computeCost(order);
    }

    @Override
    public void processOrder(Order order) throws Exception {
        DatasourceUtil.open(dataSource);
        Connection connection = DatasourceUtil.getConnection();
        connection.setAutoCommit(false);
        try {

            target.processOrder(order);

            connection.commit();
        } catch (Exception exception) {
            connection.rollback();
            throw exception;
        }
        finally {
            DatasourceUtil.close();
        }

    }
}
