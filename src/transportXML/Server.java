package transportXML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
	private static Selector selector = null;
	private static ServerSocketChannel serverSocketChannel = null;
	private final static Logger logger = Logger.getLogger(Server.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.socket().setReuseAddress(true);
			serverSocketChannel.bind(new InetSocketAddress(8888));
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("bind ok");
			while (selector.select() > 0) {
				// Someone is ready for I/O, get the ready keys
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();

				// Walk through the ready keys collection and process date requests.
				while (it.hasNext()) {
					SelectionKey readyKey = it.next();
					it.remove();

					// The key indexes into the selector so you
					// can retrieve the socket that's ready for I/O
					SocketChannel socketChannel = serverSocketChannel.accept();
					receiveFile(socketChannel, new File("receive.xml"));

					logger.log(Level.SEVERE, "receive the xml file!");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void receiveFile(SocketChannel socketChannel, File file) throws IOException {
		FileOutputStream fos = null;
		FileChannel channel = null;

		try {
			fos = new FileOutputStream(file);
			channel = fos.getChannel();
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			StringBuffer xmlStringBuffer = new StringBuffer();
			int size = 0;
			while ((size = socketChannel.read(buffer)) != -1) {
				buffer.flip();
				//channel.write(buffer);
				System.out.println("receive once");
				byte[] array = new byte[1024];
				buffer.get(array,0,buffer.remaining());
				String tempString = new String(array);
				xmlStringBuffer.append(tempString);
				buffer.clear();
				
			}
			System.out.println(xmlStringBuffer);
		} finally {
			try {
				channel.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				fos.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
