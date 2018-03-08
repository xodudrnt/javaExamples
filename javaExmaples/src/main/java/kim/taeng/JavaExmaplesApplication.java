package kim.taeng;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kim.taeng.service.threading.excutor.ExcutorExampleService;
import kim.taeng.service.threading.producer_consumer.ProducerConsumerExampleService;
import kim.taeng.service.threading.synchrronized.SynchronizedExampleService;

@SpringBootApplication
public class JavaExmaplesApplication implements CommandLineRunner {

	private static final Logger LOGGER = Logger.getLogger(JavaExmaplesApplication.class);

	@Autowired
	ExcutorExampleService excutorExampleService;
	@Autowired
	SynchronizedExampleService synchronizedExampleService;
	@Autowired
	ProducerConsumerExampleService customQueueExampleService;

	public static void main(String[] args) {
		SpringApplication.run(JavaExmaplesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Main Thread Started");
		// executor 를 통한 thread 생성자 - 소비자 예제
		// excutorExampleService.exmapleMain(2, 4);

		// Syncronized example
		// synchronizedExampleService.exmapleMain(2, 4);

		customQueueExampleService.exampleMain();
	}

}
