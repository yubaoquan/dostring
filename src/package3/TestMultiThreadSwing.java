package package3;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestMultiThreadSwing extends JFrame implements Runnable {

	private JPanel panel = new JPanel();
	private JButton button_ok = new JButton("OK");
	private JButton button_cancel = new JButton("不OK");
	/*private Panel panel = new Panel();
	private Button button_ok = new Button("OK");
	private Button button_cancel = new Button("不OK");*/
	
	private Thread2Class observer = null;
	private Host host;
	
	public TestMultiThreadSwing(Thread2Class observer) {
		this();
		setObserver(observer);
	}
	
	private void tellObserver () {
		host.passCommand();
	}
	
	public TestMultiThreadSwing() {
		super("Main Frame");
		panel.setLayout(new FlowLayout());
		panel.add(button_ok);
		button_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int sleepTime = 3;
					System.out.println("will sleep " + sleepTime + " second");
					tellObserver();
					TestMultiThreadSwing.this.setVisible(false);
					Thread.sleep(sleepTime * 1000);
					TestMultiThreadSwing.this.setVisible(true);
					System.out.println("wake up!");
					tellObserver();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		panel.add(button_cancel);
		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(250, 150);
		this.setLocation(400, 200);
		this.setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Thread2Class observer = new Thread2Class();
		TestMultiThreadSwing mainFrame = new TestMultiThreadSwing();
		Host host = new Host(mainFrame,observer);
		mainFrame.setHost(host);
		Thread thread1 = new Thread(mainFrame);
		Thread thread2 = new Thread(observer);
		Thread thread3 = new Thread(host);
		thread1.start();
		thread2.start();
		thread3.start();
	}
	
	public void setObserver(Thread2Class observer) {
		this.observer = observer;
	}

	public void setHost(Host host) {
		this.host = host;
	}

}
