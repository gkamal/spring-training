package tenx.store.infra;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggingWrapper {
	
	public static Object wrap(final Object target) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), 
				new InvocationHandler() {
					@Override
					public Object invoke(Object arg0, Method m, Object[] args)
							throws Throwable {
						System.out.println("Begin" + m.getName() + "with " + args );
						try {
							return m.invoke(target, args);
						} finally {
							System.out.println("End " + m.getName());
						}
					}
				});
	}

}
