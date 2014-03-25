package testSocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	private final static Logger logger = Logger.getLogger(Server.class.getName());
	private static Selector selector = null;
	private static ServerSocketChannel serverSocketChannel = null;
	private static SocketChannel socketChannel = null;
	private static ByteBuffer byteBuffer = null;

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

				// Walk through the ready keys collection and process date requests.
				if (it.hasNext()) {
					SelectionKey readyKey = it.next();
					it.remove();
					socketChannel = serverSocketChannel.accept();
					logger.log(Level.INFO, "A client connected!");
					talk();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				selector.close();
				socketChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void talk() throws Exception {
		byteBuffer = ByteBuffer.allocateDirect(1024);
		StringBuffer stringBuffer = new StringBuffer();
		while (socketChannel.read(byteBuffer) != -1) {
			//byteBuffer.flip();
			byte[] array = new byte[1024];
			byteBuffer.get(array);
			stringBuffer.append(array);
		}
		System.out.println(stringBuffer);
	}
}
