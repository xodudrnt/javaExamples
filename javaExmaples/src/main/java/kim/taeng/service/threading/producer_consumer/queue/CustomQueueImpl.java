package kim.taeng.service.threading.producer_consumer.queue;

import java.util.NoSuchElementException;

public interface CustomQueueImpl {

	public void clear();

	public void put(Object obj);

	public Object pop() throws InterruptedException, NoSuchElementException;

	public int size();

	
	
}
