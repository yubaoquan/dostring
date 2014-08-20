package netLearning;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	Socket socket;
	BufferedReader in;
	
	DataOutputStream dos = null;
	DataInputStream dis = null;

	public Client() {
		try {
			System.out.println("Try to Connect to 127.0.0.1:10000");
			socket = new Socket("127.0.0.1", 10000);
			System.out.println("The Server Connected!");
			System.out.println("Please enter some Character:");
			Scanner sc = new Scanner(System.in);
			String msg = sc.nextLine();
			//msg = "你好世界";
			msg = new String(msg.getBytes("GBK"));
			 this.dis = new DataInputStream(this.socket.getInputStream());
             this.dos = new DataOutputStream(this.socket.getOutputStream());
             dos.writeUTF(msg);
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println(in.readLine());
			
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
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
			if (this.dos != null) {
				try {
					this.dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.dos = null;
			}
		}
		
	}

	public static void main(String[] args) {
		new Client();
	}
}