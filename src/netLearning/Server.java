package netLearning;

import java.net.*;
import java.io.*;

public class Server {
	private ServerSocket ss;
	private Socket socket;
	
	private PrintWriter out;
	DataInputStream dis = null;

	public Server() {
		try {
			ss = new ServerSocket(10000);
			while (true) {
				socket = ss.accept();
				String RemoteIP = socket.getInetAddress().getHostAddress();
				String RemotePort = ":" + socket.getLocalPort();
				System.out.println("A client come in!IP:" + RemoteIP + RemotePort);
				
				this.dis = new DataInputStream(this.socket.getInputStream());
				String msg = dis.readUTF();
				//msg = "你好世界";
				System.out.println("msg: " + msg);
				out = new PrintWriter(socket.getOutputStream(), true);
				out.println("Your Message Received!");
				out.close();
				socket.close();
			}
		} catch (IOException e) {
			out.println("wrong");
		} finally {
			if (this.dis != null) {
				try {
					this.dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.dis = null;
			}
		}
	}

	public static void main(String[] args) {
		new Server();
	}
}