package kim.taeng.service.threading.fork_join;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ForkJoinExampleService {
	private static final Logger LOGGER = Logger.getLogger(ForkJoinExampleService.class);

	public void recusiveActionExampleMain() {
		LOGGER.info("RecusiveActionExampleMain Started");
		ForkJoinPool pool = new ForkJoinPool();
		RecursiveActionExample myRecursiveAction = new RecursiveActionExample(24);
		pool.invoke(myRecursiveAction);
		LOGGER.info("RecusiveActionExampleMain Ended");
	}

	public void recusiveTaskExampleMain() {
		LOGGER.info("RecusiveTaskExampleMain Started");
		ForkJoinPool pool = new ForkJoinPool();
		RecursiveTaskExample system = new RecursiveTaskExample("c:\\Windows", "log");
		RecursiveTaskExample apps = new RecursiveTaskExample("c:\\Program Files", "log");
		RecursiveTaskExample documents = new RecursiveTaskExample("c:\\Documents And Settings", "log");
		pool.execute(system);
		pool.execute(apps);
		pool.execute(documents);
		do {
			LOGGER.info("=========================================> INFO");
			LOGGER.info("Main : Parallelism : " + pool.getParallelism());
			LOGGER.info("Main : Active Threads : " + pool.getActiveThreadCount());
			LOGGER.info("Main : Task Count : " + pool.getQueuedTaskCount());
			LOGGER.info("Main : Steal Count : " + pool.getStealCount());
			LOGGER.info("=========================================");
		} while ((!system.isDone()) || (!apps.isDone()) || (!documents.isDone()));
		pool.shutdown();
		List<String> results;
		LOGGER.info("=========================================> RESULTS");
		results = system.join();
		LOGGER.info("System: " + results.size() + " files found.\n");
		results = apps.join();
		LOGGER.info("Apps: " + results.size() + " files found.\n");
		results = documents.join();
		LOGGER.info("Documents:" + results.size() + "files found.\n");

		LOGGER.info("RecusiveTaskExampleMain Ended");
	}
}
