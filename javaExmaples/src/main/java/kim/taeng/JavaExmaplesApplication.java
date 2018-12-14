package kim.taeng;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import kim.taeng.service.aoptest.AopTestService;
import kim.taeng.service.chatServer.ServerExampleService;
import kim.taeng.service.threading.countdownlatch.CountDownLatchExampleService;
import kim.taeng.service.threading.excutor.ExcutorExampleService;
import kim.taeng.service.threading.fork_join.ForkJoinExampleService;
import kim.taeng.service.threading.future.FutureExampleService;
import kim.taeng.service.threading.join.JoinExampleService;
import kim.taeng.service.threading.producer_consumer.ProducerConsumerExampleService;
import kim.taeng.service.threading.synchrronized.SynchronizedExampleService;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JavaExmaplesApplication implements CommandLineRunner {

	private static final Logger LOGGER = Logger.getLogger(JavaExmaplesApplication.class);

	@Autowired
	ExcutorExampleService excutorExampleService;
	@Autowired
	SynchronizedExampleService synchronizedExampleService;
	@Autowired
	ProducerConsumerExampleService producerConsumerExampleService;
	@Autowired
	ForkJoinExampleService forkJoinExampleService;
	@Autowired
	CountDownLatchExampleService countLatchExampleService;
	@Autowired
	JoinExampleService joinExampleService;
	@Autowired
	FutureExampleService futureExampleService;
	@Autowired
	ServerExampleService serverExampleService;
	@Autowired
	AopTestService aopTest;

	/* Bean List 확인 */
	// @Autowired
	// public ApplicationContext ctx;

	public static void main(String[] args) {
		SpringApplication.run(JavaExmaplesApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Main Thread Started");
		/* executor 를 통한 thread 생성자 - 소비자 예제 */
		excutorExampleService.exmapleMain(2, 4);

		/* Syncronized 예제 */
		synchronizedExampleService.exmapleMain(2, 4);

		/* producerConsumer 예제 */
		producerConsumerExampleService.exampleMain();

		/* JoinExampleService 예제 */
		joinExampleService.exmapleMain(5);

		/* Fork Join 예제 */
		// forkJoinExampleService.recusiveActionExampleMain();
		// forkJoinExampleService.recusiveTaskExampleMain();

		/* Future + Excutor 예제 */
		// futureExampleService.futureExampleMain(10, 10);

		/* NIO Chat Server 예제 */
		// serverExampleService.ServiceMain();

		/* Bean List 확인 */
		// String[] beanNames = ctx.getBeanDefinitionNames();
		// Arrays.sort(beanNames);
		// for (String beanName : beanNames) {
		// System.out.println(beanName);
		// }

		LOGGER.info("Main Thread Ended");

	}

}
