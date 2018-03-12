package kim.taeng.service.threading.countlatch;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CountLatchExampleService {

	private static final Logger LOGGER = Logger.getLogger(CountLatchExampleService.class);
	private final static int THREAD_MAX = 10;
	private static CountDownLatch latch = new CountDownLatch(THREAD_MAX);

	public void exampleMain() {
		LOGGER.info("CountLatchExampleService Started");
		for (int i = 0; i < THREAD_MAX; i++) {
			new Thread(new ExampleRunnable()).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.info("CountLatchExampleService Ended");
	}

}
