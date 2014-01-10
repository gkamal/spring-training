package fk.training.spring.internal;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kamal.govindraj on 10/01/14.
 */
public class JdbcBaseRepository {
    protected final DataSource dataSource;

    public JdbcBaseRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected void closeSilently(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                // ignoring
            }
        }
    }

    protected void closeSilently(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // ignoring
            }
        }
    }

    protected void closeSilently(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // ignoring
            }
        }
    }
}
