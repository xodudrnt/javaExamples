package kim.taeng.service.threading.join;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import kim.taeng.service.threading.synchrronized.SynchronizedCounterRunnable;

@Service
public class JoinExampleService {

	private static final Logger LOGGER = Logger.getLogger(SynchronizedCounterRunnable.class);

	public void exmapleMain(int threadMax) {
		LOGGER.info("JoinExampleService Started");
		LOGGER.info("JoinExampleService --> Thread size : " + threadMax);
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < threadMax; i++) {
			Thread t = new JoinCounterThread();
			t.start();
			threads.add(t);
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
			}
		}
		LOGGER.info("JoinExampleService Ended");
	}

}
