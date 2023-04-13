package application.application;

import java.util.Scanner;

public class Main {
	
	static MainSequence ms;
	static InputSequence is;
	
	public static void main(String[] args) {
		
		start();
		
	}

	private static void start() {

		ms = new MainSequence();
		ms.start();
		is = new InputSequence();
		is.start();
		
	}
	
}

class InputSequence implements Runnable {

	Thread inputThread;
	
	public void start() {
		inputThread = new Thread(this);
		inputThread.start();
	}
	
	public void stop() { inputThread = null; }
	
	@Override
	public void run() {

		while(inputThread != null) {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print(">");
			String cmd = sc.nextLine();
			
			if (cmd.equalsIgnoreCase("end")) {
				System.out.println("Done.");
				inputThread = null;
				Main.ms.stop();
				sc.close();
			}
			
		}
		
	}
	
}

class MainSequence implements Runnable {

	Thread mainThread;
	public final String path = "";
	
	public void start() {
		mainThread = new Thread(this);
		mainThread.start();
	}
	
	public void stop() { mainThread = null; }
	
	@Override
	public void run() {

		while (mainThread != null) {
			
			
			
		}
		
	}
	
}
