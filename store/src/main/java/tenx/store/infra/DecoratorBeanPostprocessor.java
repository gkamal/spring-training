package tenx.store.infra;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

//@Component
public class DecoratorBeanPostprocessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean.getClass().getName().startsWith("tenx.store")) {
		return LoggingWrapper.wrap(bean);
		} else {
			return bean;
		}
	}

	
	
}
