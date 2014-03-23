package learningSwing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
//import static learningSwing.SwingConsole.*;

public class LongRunningTask extends JFrame{
	private JButton b1 = new JButton("Start Long Running Task");
	private JButton b2 = new JButton("End Long Running Task");
	
	public LongRunningTask() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt){
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					System.out.println("Task Interrupted!");
					return;
				}
				System.out.println("Task Completed!");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Thread.currentThread().interrupt();
			}
		});
		setLayout(new FlowLayout());
		add(b1);
		add(b2);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingConsole.run(new LongRunningTask(),200,150);

	}
}
