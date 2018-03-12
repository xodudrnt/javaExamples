package kim.taeng.service.threading.countlatch;

import java.util.Random;

import org.apache.log4j.Logger;

public class ExampleRunnable implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(ExampleRunnable.class);

	private static int count = 0;
	private int id;

	private Random random = new Random(System.currentTimeMillis());

	public ExampleRunnable() {
		this.id = ++count;
	}

	@Override
	public void run() {
		LOGGER.info("Runnable : " + id + " Example Runnable Started");
		try {
			int delay = random.nextInt(1000) + 1000;
			LOGGER.info("Runnable : " + id + " Sleep " + delay + "ms");
			Thread.sleep(delay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Runnable : " + id + " Example Runnable Ended");
	}
}
