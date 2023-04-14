package application.application;

public class MainSequence implements Runnable {

	public final String path = "";
	
	public void start() {
		Main.mainThread = new Thread(this);
		Main.mainThread.start();
	}
		
	@Override
	public void run() {
		
		while (Main.active) {
			
			System.out.println(Main.active + "");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		System.out.println("break");
		
	}
	
}
