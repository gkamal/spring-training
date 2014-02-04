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

    protected <T> T query(String sql, JdbcProductRepository.RowMapper<T> rowMapper, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(sql);
            int index = 1;
            for(Object arg:args) {
                ps.setObject(index++, arg);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rowMapper.mapRow(rs);
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

}
