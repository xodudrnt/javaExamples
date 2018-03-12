package kim.taeng.controller.chatServer;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NonblockThread extends Thread {
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private ChatServerNonblock parent;

	public NonblockThread(Selector sel, ServerSocketChannel serverSocketChannel, ChatServerNonblock p) {
		this.selector = sel;
		this.serverSocketChannel = serverSocketChannel;
		this.parent = p;
	}

	@Override
	public void run() {
		while (true) {
			try {
				int keyCount = selector.select();
				if (keyCount == 0)
					continue;
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectedKeys.iterator();
				while (iterator.hasNext()) {

					SelectionKey selectionKey = iterator.next();

					if (selectionKey.isAcceptable()) {

					} else if (selectionKey.isReadable()) {

					} else if (selectionKey.isWritable()) {

					}
					iterator.remove();
				}

			} catch (Exception e) {
				e.printStackTrace();
				if (serverSocketChannel.isOpen())
					parent.stopServer();
				break;
			}
		}
	}
}
