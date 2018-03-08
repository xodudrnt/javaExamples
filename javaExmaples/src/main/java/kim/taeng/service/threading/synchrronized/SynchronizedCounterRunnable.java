package kim.taeng.service.threading.synchrronized;

import org.apache.log4j.Logger;

public class SynchronizedCounterRunnable implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(SynchronizedCounterRunnable.class);

	private int id;
	private static int count = 0;

	private static int i = 0;

	public SynchronizedCounterRunnable() {
		this.id = ++count;
	}

	@Override
	public void run() {
		LOGGER.info("Thread : " + id + " Started");
		for (int i = 0; i < 10; i++) {
			addIndex();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		LOGGER.info("Thread : " + id + " Ended");
	}

	private synchronized void addIndex() {
		LOGGER.info("Thread : " + id + " Count : " + ++i);
	}

}
