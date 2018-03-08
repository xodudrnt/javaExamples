package kim.taeng.service.threading.producer_consumer.queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import kim.taeng.service.threading.producer_consumer.JOB;

public class CustomQueue implements CustomQueueImpl {
	private static final Logger LOGGER = Logger.getLogger(CustomQueue.class);
	private static final Object LOCK = new Object();

	private static CustomQueue cq = new CustomQueue();
	private LinkedList<Object> jobs = new LinkedList<Object>();

	private CustomQueue() {

	}

	public static CustomQueue getInstance() {
		if (cq == null) {
			synchronized (CustomQueue.class) {
				cq = new CustomQueue();
			}
		}
		return cq;
	}

	@Override
	public void clear() {
		synchronized (LOCK) {
			jobs.clear();
		}
	}

	@Override
	public void put(Object obj) {
		synchronized (LOCK) {
			jobs.addLast(obj);
			LOGGER.info("PUT : " + ((JOB) obj).getId());
			LOCK.notify();
		}
	}

	@Override
	public Object pop() throws InterruptedException, NoSuchElementException {
		Object obj = null;

		LOGGER.info("JOB SIZE : " + jobs.size());

		synchronized (LOCK) {
			if (jobs.isEmpty()) {
				LOGGER.info("WAIT");
				LOCK.wait();
				LOGGER.info("WAIT END");
				obj = jobs.removeFirst();
			} else {
				obj = jobs.removeFirst();
			}
		}
		if (obj == null)
			throw new NoSuchElementException();
		else
			LOGGER.info("POP : " + ((JOB) obj).getId());
		return obj;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return jobs.size();
	}

}
