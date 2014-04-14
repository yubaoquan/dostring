package testSocketChannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client {
	private static SocketChannel socketChannel = null;
	private static SocketAddress socketAddress = null;
	private static ByteBuffer byteBuffer = null;
	private static Selector selector = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		socketAddress = new InetSocketAddress("localhost", 8888);
		try {
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			socketChannel.connect(socketAddress);
			//talk();
			listen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void listen() throws Exception {
		while (true) {
			// 选择一组键，其相应的通道已为 I/O 操作准备就绪。
			// 此方法执行处于阻塞模式的选择操作。
			selector.select();
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> it = selectionKeys.iterator();
			while (it.hasNext()) {
				SelectionKey selectionKey = it.next();
				handleKey(selectionKey);

			}
			selectionKeys.clear();
		}
	}

	private static void handleKey(SelectionKey selectionKey) throws Exception {
	
		if (selectionKey.isConnectable()) {
			System.out.println("client connect");
			socketChannel = (SocketChannel) selectionKey.channel();
			// 判断此通道上是否正在进行连接操作。
			// 完成套接字通道的连接过程。
			if (socketChannel.isConnectionPending()) {
				socketChannel.finishConnect();
				System.out.println("完成连接!");
				/*byteBuffer.clear();
				byteBuffer.put("Hello,Server".getBytes());
				byteBuffer.flip();
				socketChannel.write(byteBuffer);*/
				byteBuffer = ByteBuffer.wrap("你好，世界 | こんにちは、世界中のみなさん".getBytes("UTF-8"));
				socketChannel.write(byteBuffer);
				byteBuffer.clear();
				System.out.println("send finished!");
				socketChannel.register(selector, SelectionKey.OP_READ);
			}
		} else if (selectionKey.isReadable()) {
			socketChannel = (SocketChannel) selectionKey.channel();
			// 将缓冲区清空以备下次读取
			byteBuffer.clear();
			
			StringBuffer stringBuffer = new StringBuffer();
			int size = 0;
			while ((size = socketChannel.read(byteBuffer)) > 0) {
				byteBuffer.flip();
				byte[] array = new byte[1024];
				byteBuffer.get(array, 0, size);
				stringBuffer.append(new String(array));
				byteBuffer.clear();
			}
			System.out.println("receive: " + stringBuffer);
			socketChannel.register(selector, SelectionKey.OP_WRITE);
		} else if (selectionKey.isWritable()) {
			byteBuffer.clear();
			socketChannel = (SocketChannel) selectionKey.channel();
			byteBuffer = ByteBuffer.wrap("再次发送数据".getBytes("UTF-8"));
			int size = socketChannel.write(byteBuffer);
			byteBuffer.clear();
			byte[] array = new byte[1024];
			byteBuffer.get(array, 0, size);
			String string = new String(array).trim();
			System.out.println("客户端向服务器端发送数据--：" + string);
			socketChannel.register(selector, SelectionKey.OP_READ);
			socketChannel.socket().close();
			socketChannel.close();
			System.exit(0);
		}
	}

	public static void sendFile(File file, SocketChannel socketChannel) {
		FileChannel fileChannel = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			fileChannel = fileInputStream.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
			int size = 0;
			while ((size = fileChannel.read(byteBuffer)) != -1) {
				byteBuffer.flip();
				socketChannel.write(byteBuffer);
				byteBuffer.clear();
				System.out.println("write once");
			}
			socketChannel.socket().shutdownOutput();
			fileChannel.close();
			fileInputStream.close();

			byteBuffer.clear();
			int receive_size = 0;
			StringBuffer xmlStringBuffer = new StringBuffer();
			while ((receive_size = socketChannel.read(byteBuffer)) != -1) {
				System.out.println("there2");
				byteBuffer.flip();
				System.out.println("receive once");
				byte[] array = new byte[1024];
				byteBuffer.get(array, 0, byteBuffer.remaining());
				String tempString = new String(array);
				xmlStringBuffer.append(tempString);
				byteBuffer.clear();
			}

			String xmlData = new String(xmlStringBuffer);
			System.out.println(xmlData);
			socketChannel.socket().shutdownOutput();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileChannel.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				fileInputStream.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
}