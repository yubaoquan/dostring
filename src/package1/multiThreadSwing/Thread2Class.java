package package1.multiThreadSwing;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Thread2Class extends JFrame implements Runnable{
	
	private Panel panel = new Panel();
	private Button button_ok = new Button("OK");
	String message = "the main frame is sleeping\n ";
	private TextComponent textArea = new TextArea(message);
	
	private boolean visiable = false;
	
	public Thread2Class(boolean visiable) {
		this();
		this.setVisible(visiable);
	}
	
	public Thread2Class() {
		panel.setLayout(new BorderLayout());
		textArea.setEditable(false);
		panel.add(textArea,BorderLayout.CENTER);
		panel.add(button_ok,BorderLayout.SOUTH);
		button_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button pressed");

			}

		});
		this.add(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(250, 150);
		this.setLocation(300, 200);
		this.setVisible(visiable);
	}

	public void react2Command() {
		visiable = !visiable;
		this.setVisible(visiable);
	}
	
	@Override
	public void run() {
		int dotLength = 0;
		while (true) {
			try {
				if (dotLength == 10) {
					dotLength = 0;
				}
				Thread.sleep(700);
				String text = "observer running ... ";
				
				textArea.setText(text + dotLength);
				dotLength ++;
				System.out.println("observer running " + text);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}