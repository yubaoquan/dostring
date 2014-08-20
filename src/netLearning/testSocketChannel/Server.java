package netLearning.testSocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	private final static Logger logger = Logger.getLogger(Server.class.getName());
	private static Selector selector = null;
	private static ServerSocketChannel serverSocketChannel = null;
	private static SocketChannel clientSocketChannel = null;
	private static ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
	private static int closeSwitch = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			selector = Selector.open();

			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(8888));
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("bind ok");

			while (selector.select() > 0) {
				// Someone is ready for I/O, get the ready keys
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();

				// Walk through the ready keys collection and process date
				// requests.
				if (it.hasNext()) {
					SelectionKey readyKey = it.next();
					it.remove();

					if (readyKey.isAcceptable()) {
						serverSocketChannel = (ServerSocketChannel) readyKey.channel();
						clientSocketChannel = serverSocketChannel.accept();
						clientSocketChannel.configureBlocking(false);
						clientSocketChannel.register(selector, SelectionKey.OP_READ);
					}
					logger.log(Level.INFO, "A client connected!");
					talk(readyKey);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				selector.close();
				clientSocketChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void talk(SelectionKey selectionKey) throws Exception {
		if (selectionKey.isReadable()) {
			clientSocketChannel = (SocketChannel) selectionKey.channel();
			StringBuffer stringBuffer = new StringBuffer();
			int size = 0;
			byteBuffer = ByteBuffer.allocateDirect(1024);
			while ((size = clientSocketChannel.read(byteBuffer)) > 0) {
				System.out.println("size: " + size);
				byteBuffer.flip();
				byte[] array = new byte[1024];
				byteBuffer.get(array, 0, byteBuffer.remaining());
				byteBuffer.compact();
				stringBuffer.append(new String(array).trim());
				byteBuffer.clear();
			}
			System.out.println(stringBuffer.toString());
			if (stringBuffer.toString().equals("再次发送数据")) {
				releaseResourcesAndExit();
			}
			clientSocketChannel.register(selector, SelectionKey.OP_WRITE);
		} else if (selectionKey.isWritable()) {
			byteBuffer.clear();
			byteBuffer = ByteBuffer.wrap("收到消息!".getBytes("UTF-8"));
			clientSocketChannel.write(byteBuffer);
			byteBuffer.clear();
			clientSocketChannel.register(selector, SelectionKey.OP_READ);

		}
	}

	private static void releaseResourcesAndExit() throws Exception {
		clientSocketChannel.socket().shutdownOutput();
		clientSocketChannel.close();
		System.exit(0);
	}
}