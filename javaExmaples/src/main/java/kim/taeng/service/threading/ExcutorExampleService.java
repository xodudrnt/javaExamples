package kim.taeng.service.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ExcutorExampleService {
	private static final Logger LOGGER = Logger.getLogger(ExcutorExampleService.class);

	public void exmapleMain(int threadPoolMax, int threadMax) {
		
		LOGGER.info("ExcutorExampleService --> Thread Pool size : "+ threadPoolMax);
		LOGGER.info("ExcutorExampleService --> Thread Pool size : "+ threadMax);
		
		ExecutorService executorService = Executors.newFixedThreadPool(threadPoolMax);
		for (int i = 0; i < threadMax; i++)
			executorService.execute(new CounterRunnable());
		executorService.shutdown();
	}
}
