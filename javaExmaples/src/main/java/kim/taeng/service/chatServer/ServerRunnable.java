package kim.taeng.service.chatServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.log4j.Logger;

public class ServerRunnable implements Runnable {

	private static final int BUFF_MAX = 512;
	private static final Logger LOGGER = Logger.getLogger(ServerRunnable.class);
	private final int port;
	private ServerSocketChannel ssc;
	private Selector selector;
	private ByteBuffer buf = ByteBuffer.allocate(BUFF_MAX);

	ServerRunnable(int port) throws IOException {
		this.port = port;
		this.ssc = ServerSocketChannel.open();
		this.ssc.socket().bind(new InetSocketAddress(port));
		this.ssc.configureBlocking(false);
		this.selector = Selector.open();
		this.ssc.register(selector, SelectionKey.OP_ACCEPT);
	}

	@Override
	public void run() {
		try {
			LOGGER.info("Server starting on port " + this.port);
			Iterator<SelectionKey> iter;
			SelectionKey key;
			while (this.ssc.isOpen() && !Thread.interrupted()) {
				selector.select();
				iter = this.selector.selectedKeys().iterator();
				while (iter.hasNext() && !Thread.interrupted()) {
					key = iter.next();
					iter.remove();
					if (key.isAcceptable())
						this.handleAccept(key);
					if (key.isReadable())
						this.handleRead(key);
				}
			}
		} catch (IOException e) {
			LOGGER.info("IOException, server of port " + this.port + " terminating. Stack trace:");
			e.printStackTrace();
		} finally {
			LOGGER.info("Thread Interrupted");
		}
	}

	private final ByteBuffer welcomeBuf = ByteBuffer.wrap("Welcome to NioChat!\n".getBytes());

	private void handleAccept(SelectionKey key) throws IOException {
		SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
		String address = (new StringBuilder(sc.socket().getInetAddress().toString())).append(":")
				.append(sc.socket().getPort()).toString();
		sc.configureBlocking(false);
		sc.register(selector, SelectionKey.OP_READ, address);
		sc.write(welcomeBuf);
		welcomeBuf.rewind();
		LOGGER.info("accepted connection from: " + address);
	}

	private void handleRead(SelectionKey key) throws IOException {
		SocketChannel ch = (SocketChannel) key.channel();
		StringBuilder sb = new StringBuilder();

		buf.clear();
		int read = 0;
		while ((read = ch.read(buf)) > 0) {
			buf.flip();
			byte[] bytes = new byte[buf.limit()];
			buf.get(bytes);
			sb.append(new String(bytes));
			buf.clear();
		}

		String msg;
		if (read < 0) {
			msg = key.attachment() + " left the chat.\n";
			ch.close();
		} else
			msg = key.attachment() + ": " + sb.toString();

		LOGGER.info(msg);
		broadcast(msg);
	}

	private void broadcast(String msg) throws IOException {
		ByteBuffer msgBuf = ByteBuffer.wrap(msg.getBytes());
		for (SelectionKey key : selector.keys()) {
			if (key.isValid() && key.channel() instanceof SocketChannel) {
				SocketChannel sch = (SocketChannel) key.channel();
				sch.write(msgBuf);
				msgBuf.rewind();
			}
		}
	}
}
