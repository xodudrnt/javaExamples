package kim.taeng.service.chatServer;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ServerExampleService {
	private static final int PORT = 9999;
	private static final Logger LOGGER = Logger.getLogger(ServerExampleService.class);
	private ServerRunnable server = null;
	private Thread th = null;

	public ServerExampleService() {

	}

	public void ServiceMain() throws InterruptedException {
		this.start();
		Thread.sleep(5000);
		this.stop();
	}

	public void start() {
		LOGGER.info("Server Start !");
		try {
			server = new ServerRunnable(PORT);
			th = new Thread(server);
			th.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		if (th != null)
			th.interrupt();
		LOGGER.info("Server Stoped !");
	}

}
