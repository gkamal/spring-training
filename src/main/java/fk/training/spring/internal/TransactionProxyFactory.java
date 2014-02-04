package fk.training.spring.internal;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class TransactionProxyFactory {

    private final DataSource dataSource;

    public TransactionProxyFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public  Object wrap(final Object target, Class... interf) {

       return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                interf, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {

                DatasourceUtil.open(dataSource);
                Connection connection = DatasourceUtil.getConnection();
                connection.setAutoCommit(false);
                try {

                    Object returnValue =method.invoke(target, args);

                    connection.commit();

                    return returnValue;
                } catch (Exception exception) {
                    connection.rollback();
                    throw exception;
                }
                finally {
                    DatasourceUtil.close();
                }
            }
        });

    }

}
