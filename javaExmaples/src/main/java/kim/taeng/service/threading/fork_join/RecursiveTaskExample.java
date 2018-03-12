package kim.taeng.service.threading.fork_join;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import org.apache.log4j.Logger;

public class RecursiveTaskExample extends RecursiveTask<List<String>> {

	private static final Logger LOGGER = Logger.getLogger(RecursiveTaskExample.class);

	private static final long serialVersionUID = 1L;
	private final String path;
	private final String extension;

	public RecursiveTaskExample(String path, String extension) {
		this.path = path;
		this.extension = extension;
	}

	@Override
	protected List<String> compute() {
		// TODO Auto-generated method stub

		List<String> list = new ArrayList<String>();
		List<RecursiveTaskExample> tasks = new ArrayList<RecursiveTaskExample>();
		File f = new File(path);
		File content[] = f.listFiles();
		for (File subFile : content) {
			if (subFile.isDirectory()) {
				RecursiveTaskExample task = new RecursiveTaskExample(subFile.getAbsolutePath(), extension);
				task.fork();
				tasks.add(task);
			} else {
				if (checkFille(subFile.getName())) {
					list.add(subFile.getAbsolutePath());
				}
			}
		}
		if (tasks.size() > 50) {
			LOGGER.info(f.getAbsolutePath() + " : " + tasks.size() + " tasks");
		}
		addResultsFromTasks(list, tasks);
		return list;
	}

	private void addResultsFromTasks(List<String> list, List<RecursiveTaskExample> tasks) {
		// TODO Auto-generated method stub
		for (RecursiveTaskExample item : tasks) {
			list.addAll(item.join());
		}
	}

	private boolean checkFille(String name) {
		// TODO Auto-generated method stub
		return name.endsWith(extension);
	}

}
