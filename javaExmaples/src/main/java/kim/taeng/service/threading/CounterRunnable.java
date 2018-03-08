package kim.taeng.service.threading;

import org.apache.log4j.Logger;

public class CounterRunnable implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(CounterRunnable.class);
	
	private static int count = 0;
	private int id;

	public CounterRunnable() {
		this.id = ++count;
	}

	@Override
	public void run() {
		LOGGER.info("Thread : " + id + " Started");
		for (int i = 0; i < 10; i++) {
			LOGGER.info("Thread : " + id + " Count : " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		LOGGER.info("Thread : " + id + " Ended");
	}
}
