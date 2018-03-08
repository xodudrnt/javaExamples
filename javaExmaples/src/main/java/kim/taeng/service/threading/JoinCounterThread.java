package kim.taeng.service.threading;

import org.apache.log4j.Logger;

public class JoinCounterThread extends Thread {

	private static final Logger LOGGER = Logger.getLogger(JoinCounterThread.class);

	private int id;
	private static int count = 0;

	public JoinCounterThread() {
		this.id = ++count;
	}

	@Override
	public void run() {
		LOGGER.info("Thread : " + id + " Started");
		for (int i = 0; i < 10; i++) {
			LOGGER.info("Thread : " + id + " Count : " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		LOGGER.info("Thread : " + id + " Ended");
	}
}
