package netLearning.transportXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class Client {
	private static SocketChannel socketChannel = null;
	private static SocketAddress socketAddress = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		socketAddress = new InetSocketAddress("localhost",8888);
		try {
			socketChannel = SocketChannel.open();
			socketChannel.connect(socketAddress);
			String filePath = "E:\\code\\ecli\\WS\\pom.xml";
			sendFile(new File(filePath), socketChannel);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			while((size = fileChannel.read(byteBuffer)) != -1) {
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
				byteBuffer.get(array,0,byteBuffer.remaining());
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
		}  finally {
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
