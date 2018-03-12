package kim.taeng.service.threading.fork_join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import org.apache.log4j.Logger;

public class RecursiveActionExample extends RecursiveAction {
	private static final Logger LOGGER = Logger.getLogger(RecursiveActionExample.class);

	private static int count = 0;
	private int id;

	private long workLoad = 0;
	private static final long WORK_LOAD_MAX = 3;

	public RecursiveActionExample(long workLoad) {
		// TODO Auto-generated constructor stub
		this.id = ++count;
		this.workLoad = workLoad;
	}

	@Override
	protected void compute() {
		LOGGER.info("RecursiveActionExample : " + id + " Started");
		// TODO Auto-generated method stub

		if (this.workLoad > WORK_LOAD_MAX) {
			LOGGER.info("Splitting work load : " + this.workLoad);
			List<RecursiveActionExample> subTasks = new ArrayList<RecursiveActionExample>();

			subTasks.addAll(createSubTasks());

			for (RecursiveAction subTask : subTasks)
				subTask.fork();
		} else {
			LOGGER.info("NOT Splitting work load : " + this.workLoad);
		}

		LOGGER.info("RecursiveActionExample : " + id + " Ended");
	}

	private List<RecursiveActionExample> createSubTasks() {
		List<RecursiveActionExample> tasks = new ArrayList<RecursiveActionExample>();
		tasks.add(new RecursiveActionExample(this.workLoad / 2));
		tasks.add(new RecursiveActionExample(this.workLoad / 2));
		return tasks;
	}

}
