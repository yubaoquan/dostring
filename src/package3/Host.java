package package3;

import package3.Thread2Class;

public class Host implements Runnable{
	private TestMultiThreadSwing mainFrame;
	private Thread2Class observer;
	private boolean commandReceived = false;
	
	
	public Host(TestMultiThreadSwing mainFrame, Thread2Class observer) {
		this.mainFrame = mainFrame;
		this.observer = observer;
		
	}
	
	public void passCommand() {
		//System.out.println("passCommand");
		observer.react2Command();
		this.setCommandReceived(false);
	}
	
	public void setMainFrame(TestMultiThreadSwing mainFrame) {
		this.mainFrame = mainFrame;
	}
	public void setObserver(Thread2Class observer) {
		this.observer = observer;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (commandReceived) {
				passCommand();
			} else {
				//System.out.println(false);
			}
		}
		
	}

	public void setCommandReceived(boolean commandReceived) {
		this.commandReceived = commandReceived;
		//System.out.println("setCommandReceived");
	}
}