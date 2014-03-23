package kcsj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login {
	private JPanel panel = new JPanel();
	private JFrame frame = new JFrame("frame");
	
	private JLabel serverNameLabel = new JLabel("server name:");
	private JLabel userNameLabel = new JLabel("user name:");
	private JLabel passwordLabel = new JLabel("password:");
	
	private JTextField serverNameTextField = new JTextField();
	private JTextField userNameTextField = new JTextField();
	private JPasswordField passwordTextField = new JPasswordField();
	
	private JButton confiemButton = new JButton("login");
	private JButton resetButton = new JButton("reset");
	
	private GridLayout layout = new GridLayout(4,2);
	private JOptionPane optionPane = new JOptionPane();
	private JDialog dialog = new JDialog(frame,"dialog",true);
	
	public void init() {
		this.frame.setLayout(this.layout);
		this.panel.add(this.serverNameLabel);
		this.panel.add(this.serverNameTextField);
		this.panel.add(this.userNameLabel);
		this.panel.add(this.userNameTextField);
		this.panel.add(this.passwordLabel);
		this.panel.add(this.passwordTextField);
		this.panel.add(this.confiemButton);
		this.panel.add(this.resetButton);
		
		//this.button.addActionListener(new MyMonitor(this));
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
		char[] array = {'h','e','l','l','o'};
		String str = new String(array);
		System.out.println(str);

	}

	static class MyMonitor implements ActionListener {
		private Login test1;
		MyMonitor(Login test1) {
			this.test1 = test1;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			int selection = test1.optionPane.showConfirmDialog(test1.frame, (Object)"message");
			System.out.println(selection);
		}
	}

}
