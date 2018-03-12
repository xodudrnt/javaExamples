package kim.taeng.service.threading.future;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import kim.taeng.model.future.ResultModel;
import kim.taeng.utils.Commons;

public class FutureTask implements Callable {

	private static Logger LOGGER = Logger.getLogger(FutureTask.class);
	private int id;
	private static int count = 0;

	public FutureTask() {
		this.id = ++count;
	}

	@Override
	public Object call() throws Exception {
		long delay = new Commons().getRandomTime();
		Thread.sleep(delay);
		ResultModel rm = new ResultModel();
		rm.setId(this.id);
		rm.setDelay(delay);
		rm.setMessage("This Result From" + this.id);
		LOGGER.info(rm.toString());
		return rm;
	}
}
