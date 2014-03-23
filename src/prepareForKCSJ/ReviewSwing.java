package prepareForKCSJ;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReviewSwing {
	private JPanel panel = new JPanel();
	private JFrame frame = new JFrame("邮件代理系统");
	private JLabel label = new JLabel("label");
	private JButton button = new JButton("button");
	private BorderLayout layout = new BorderLayout();
	private JOptionPane optionPane = new JOptionPane();
	private JDialog dialog = new JDialog(frame,"dialog",true);
	
	public void init() {
		this.frame.setLayout(this.layout);
		this.panel.add(this.label);
		this.button.addActionListener(new MyMonitor(this));
		this.panel.add(button);
		this.frame.add(this.panel);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocation(200, 100);
		this.frame.setSize(250, 150);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ReviewSwing().init();

	}

	static class MyMonitor implements ActionListener {
		private ReviewSwing test1;
		MyMonitor(ReviewSwing test1) {
			this.test1 = test1;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			int selection = test1.optionPane.showConfirmDialog(test1.frame, (Object)"message");
			System.out.println(selection);
		}
		
	}
}
