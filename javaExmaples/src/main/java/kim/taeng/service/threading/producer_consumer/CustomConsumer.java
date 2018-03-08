package kim.taeng.service.threading.producer_consumer;

import org.apache.log4j.Logger;

import kim.taeng.service.threading.producer_consumer.queue.CustomQueue;

public class CustomConsumer extends Thread {
	private static final Logger LOGGER = Logger.getLogger(CustomConsumer.class);

	private CustomQueue cq;

	public CustomConsumer(CustomQueue cq) {
		this.cq = cq;
	}

	@Override
	public void run() {
		LOGGER.info("CustomConsumer Started");
		try {
			while (!isInterrupted()) {
				((JOB) cq.pop()).run();
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
//			e.printStackTrace();
		} finally {
			LOGGER.info("CustomConsumer Ended");
		}
	}
}
