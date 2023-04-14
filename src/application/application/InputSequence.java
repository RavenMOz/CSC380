package application.application;

import java.util.Scanner;

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
		
		while(Main.active) {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print(">");
			String cmd = sc.nextLine();
			
			if (cmd.equalsIgnoreCase("end")) {
				sc.close();
				System.out.println("Done.");
				Main.stop();
				break;
			
//			} else if (cmd.equalsIgnoreCase("sql")) {
//				while (true) {
//					System.out.print("SQL >");
//					cmd = sc.next();
//					if (cmd.equalsIgnoreCase("end")) break;
//					String query = cmd;
//					try {
//						try {
//							try {
//								Connection con = DriverManager.getConnection(url, uname, pwd);
//								Statement statement = con.createStatement();
//								ResultSet result = statement.executeQuery(query);
//								try {
//									while (result.next()) {
//										String famdata = "";
//										for (int i = 1; i <= 8; i++) {
//											famdata += result.getString(i) + "; ";
//										}
//										System.out.println(famdata);
//									}
//								} catch (Exception e) {
//									while (result.next()) {
//										String famdata = "";
//										for (int i = 1; i <= 2; i++) {
//											famdata += result.getString(i) + "; ";
//										}
//										System.out.println(famdata);
//									}
//								}
//								
//							} catch (SQLException e) {
//								Connection con = DriverManager.getConnection(url, uname, pwd);
//								Statement statement = con.createStatement(); statement.execute(query);
//							}
//						} catch (SQLSyntaxErrorException e) {
//							System.out.println("Invalid Syntax! Try Again!");
//							System.out.println("See report?");
//							cmd = sc.nextLine();
//							if (cmd.contains("y")) e.printStackTrace();
//						}
//					} catch (SQLException e) {
//						System.out.println("Something went wrong! Try Again!");
//						System.out.println("See report?");
//						cmd = sc.nextLine();
//						if (cmd.contains("y")) e.printStackTrace();
//					}
//				}
			} else {
				System.out.println("Not recognized");
			}
			sc.close();
		}
		
	}
	
}
