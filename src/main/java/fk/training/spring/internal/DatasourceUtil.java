package fk.training.spring.internal;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatasourceUtil {

    static ThreadLocal<Connection> connection = new ThreadLocal<Connection>();

    public static void open(DataSource dataSource) throws SQLException {
        connection.set(dataSource.getConnection());
    }

    public static Connection getConnection() {
        return connection.get();
    }

    public static void close() throws SQLException {
        connection.get().close();
        connection.remove();
    }

}
