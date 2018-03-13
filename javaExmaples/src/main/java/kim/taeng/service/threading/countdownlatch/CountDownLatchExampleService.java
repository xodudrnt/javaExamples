package kim.taeng.service.threading.countdownlatch;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CountDownLatchExampleService {

	private static final Logger LOGGER = Logger.getLogger(CountDownLatchExampleService.class);
	private final static int THREAD_MAX = 10;
	private static CountDownLatch latch = new CountDownLatch(THREAD_MAX);

	public void exampleMain() {
		LOGGER.info("CountDownLatchExampleService Started");
		for (int i = 0; i < THREAD_MAX; i++) {
			new Thread(new ExampleRunnable()).start();
		}
		try {
			// CountDownLatch에 wait를 걸어서 기다리게 한다
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.info("CountLatchExampleService Ended");
	}

}
