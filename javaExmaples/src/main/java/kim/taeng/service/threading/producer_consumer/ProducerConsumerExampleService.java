package kim.taeng.service.threading.producer_consumer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import kim.taeng.service.threading.producer_consumer.queue.CustomQueue;

@Service
public class ProducerConsumerExampleService {
	private static final Logger LOGGER = Logger.getLogger(ProducerConsumerExampleService.class);

	private CustomQueue cq = CustomQueue.getInstance();

	public ProducerConsumerExampleService() {

	}

	public void exampleMain() {

		LOGGER.info("Consumer 생성");
		Thread consumer = new Thread(new CustomConsumer(cq));

		LOGGER.info("Consumer 실행");
		consumer.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.info("producer 생성");
		Thread producer1 = new Thread(new CustomProducer(cq));
		Thread producer2 = new Thread(new CustomProducer(cq));
		Thread producer3 = new Thread(new CustomProducer(cq));

		producer1.start();
		producer2.start();
		producer3.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		consumer.interrupt();

		producer1.interrupt();
		producer2.interrupt();
		producer3.interrupt();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		LOGGER.info("Thread 종료");
		
	}
}
