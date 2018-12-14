package kim.taeng.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class PerformanceAOP {

	private static final Logger LOGGER = Logger.getLogger(PerformanceAOP.class);

	@Around("execution(* kim..*.*(..))")
	public Object PerformenceTest(final ProceedingJoinPoint point) {
		Object result = null;
		StopWatch sw = new StopWatch();
		sw.start();
		try {
			result = point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		sw.stop();
		LOGGER.info("[Time Test] <"+point.getSignature().getName()+"> "+sw.getTotalTimeMillis()+"ms");
		return result;
	}

}
