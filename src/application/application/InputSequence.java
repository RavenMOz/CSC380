package application.application;

import java.util.Scanner;

import application.middleware.JSONWriteFunctions;

public class InputSequence implements Runnable {

	static final String url = "jdbc:mysql://localhost:3306/familytree";
	static final String uname = "testuser";
	static final String pwd = "123";
	
	public void start() {
		Main.inputThread = new Thread(this);
		Main.inputThread.start();
	}
		
	@Override
	public void run() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);

		while(Main.active) {
			
			System.out.print(">>: ");
			String cmd = sc.nextLine().toLowerCase();
			
			if (cmd.contains("end")) {
				System.out.println("Done.");
				Main.stop();
				break;
			} else if (cmd.contains("export")) {
				System.out.println("Which user to export?");
				System.out.print(">");
				long id = Long.parseLong(sc.nextLine());
				JSONWriteFunctions.createUserData(id);
			} else if (cmd.contains("save")) {
				System.out.println("Which user to save?");
				System.out.print(">");
				long id = Long.parseLong(sc.nextLine());
				JSONWriteFunctions.save(id);
				JSONWriteFunctions.dispose(id);
			} else {
				System.out.println("Not recognized");
			}
			
		}
		
		sc.close();
		
	}
	
}
