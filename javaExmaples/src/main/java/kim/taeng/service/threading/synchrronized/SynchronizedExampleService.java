package kim.taeng.service.threading.synchrronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SynchronizedExampleService {
	private static final Logger LOGGER = Logger.getLogger(SynchronizedExampleService.class);

	public void exmapleMain(int threadPoolMax, int threadMax) {
		LOGGER.info("SynchronizedExampleService Started");
		LOGGER.info("SynchronizedExampleService --> Thread Pool size : " + threadPoolMax);
		LOGGER.info("SynchronizedExampleService --> Thread Pool size : " + threadMax);

		ExecutorService executorService = Executors.newFixedThreadPool(threadPoolMax);
		for (int i = 0; i < threadMax; i++)
			executorService.execute(new SynchronizedCounterRunnable());
		executorService.shutdown();
		LOGGER.info("SynchronizedExampleService Ended");
	}
}
