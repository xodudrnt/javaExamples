package kim.taeng.service.threading.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import kim.taeng.domain.future.ResultModel;

@Service
public class FutureExampleService {

	private static final Logger LOGGER = Logger.getLogger(FutureExampleService.class);

	private ExecutorService executor;
	private List<FutureTask> futures;

	public void futureExampleMain(int threadMax, int threadPoolMax) throws InterruptedException, ExecutionException {

		LOGGER.info("FutureExample Started");
		this.executor = Executors.newFixedThreadPool(threadPoolMax);
		futures = new ArrayList<FutureTask>();
		List<Future<ResultModel>> results = new ArrayList<Future<ResultModel>>();

		LOGGER.info("FutureExample : Create FutureTask");
		for (int i = 0; i < threadMax; i++) {
			Thread.sleep(500);
			futures.add(new FutureTask());
		}

		LOGGER.info("FutureExample : submit");
		for (FutureTask future : futures) {
			Future<ResultModel> result = executor.submit(future);
			results.add(result);
			Thread.sleep(500);
		}

		LOGGER.info("FutureExample : get results");
		for (Future<ResultModel> future : results) {
			String string = future.get().toString();
			// LOGGER.info("Task Done : "+future.isDone());
		}
		
		executor.shutdown();
		LOGGER.info("FutureExample Ended");
	}
}
