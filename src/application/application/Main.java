package application.application;

public class Main {
	
	static MainSequence ms;
	static InputSequence is;
	
	public static Thread mainThread;
	public static Thread inputThread;
	
	public static boolean active;
	
	public static void main(String[] args) {
		
		start();
		
	}

	private static void start() {

		active = true;
		
		ms = new MainSequence();
		ms.start();
		is = new InputSequence();
		is.start();
		
	}
	
	public static void stop() {
		active = false;
	}
	
}




