package kim.taeng.service.threading.producer_consumer;

import org.apache.log4j.Logger;

import kim.taeng.service.threading.producer_consumer.queue.CustomQueue;

public class CustomProducer extends Thread {
	private static final Logger LOGGER = Logger.getLogger(CustomProducer.class);

	private CustomQueue cq;

	public CustomProducer(CustomQueue cq) {
		this.cq = cq;
	}

	@Override
	public void run() {
		LOGGER.info("CustomProducer Started");
		try {
			while (!isInterrupted()) {
				cq.put(new JOB());
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			
//			e.printStackTrace();
		} finally {
			LOGGER.info("CustomProducer Ended");
		}
	}
}