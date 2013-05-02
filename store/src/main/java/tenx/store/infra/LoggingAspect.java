package tenx.store.infra;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component("loggingAspect")
@Aspect
public class LoggingAspect implements Ordered {

	@Around("execution(@tenx.store.infra.Profile * *(..))")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("around - start");
		StopWatch watch = new StopWatch(pjp.getSignature().getName());
		try {
			watch.start();
			return pjp.proceed();
		} finally {
			watch.stop();
			System.out.println(watch);
		}
	}
	
	@Before("execution(* tenx.store.internal.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("Before"  + joinPoint.getSignature().getName()
				+ " " + joinPoint.getTarget() 
				+ joinPoint.getArgs());
	}
	
	@After("execution(* tenx.store.internal.*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("After " + joinPoint.getSignature().getName()
				+ " " + joinPoint.getTarget() 
				+ joinPoint.getArgs());
	}

	@Override
	public int getOrder() {
		return 100;
	}
	
	
	
}
