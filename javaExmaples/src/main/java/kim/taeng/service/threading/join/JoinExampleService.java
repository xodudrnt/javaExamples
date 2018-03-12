package kim.taeng.service.threading.join;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import kim.taeng.service.threading.synchrronized.SynchronizedCounterRunnable;

@Service
public class JoinExampleService {

	private static final Logger LOGGER = Logger.getLogger(SynchronizedCounterRunnable.class);

	public void exmapleMain(int threadPoolMax, int threadMax) {
		LOGGER.info("JoinExampleService Started");
		LOGGER.info("JoinExampleService --> Thread Pool size : " + threadPoolMax);
		LOGGER.info("JoinExampleService --> Thread Pool size : " + threadMax);

		
		
	}

}
