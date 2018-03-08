package kim.taeng.service.threading.producer_consumer;

import org.apache.log4j.Logger;

public class JOB {

	private static final Logger LOGGER = Logger.getLogger(JOB.class);
	private static int count = 0;
	private int id;

	public JOB() {
		this.id = ++count;
	}

	public void run() {
		LOGGER.info("JOB : " + id + " Started");
		for (int i = 0; i < 10; i++) {
			LOGGER.info("JOB : " + id + " Count : " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		LOGGER.info("JOB : " + id + " Ended");
	}

	public int getId() {
		return this.id;
	}
}
