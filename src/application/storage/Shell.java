package application.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Scanner;

public class Shell {
	
	static final String url = "jdbc:mysql://localhost:3306/familytree";
	static final String uname = "testuser";
	static final String pwd = "123";
	
	public static void main(String[] args) throws SQLException {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		while (true) {
			System.out.print("MySQL Shell> ");
			String cmd = sc.nextLine();
			
			if (cmd.equalsIgnoreCase("end")) break;
			
			String query = cmd;
			
			try {
				try {
					try {
						Connection con = DriverManager.getConnection(url, uname, pwd);
						Statement statement = con.createStatement();
						ResultSet result = statement.executeQuery(query);
						try {
							while (result.next()) {
								String famdata = "";
								for (int i = 1; i <= 8; i++) {
									famdata += result.getString(i) + "; ";
								}
								System.out.println(famdata);
							}
						} catch (Exception e) {
							while (result.next()) {
								String famdata = "";
								for (int i = 1; i <= 2; i++) {
									famdata += result.getString(i) + "; ";
								}
								System.out.println(famdata);
							}
						}
						
					} catch (SQLException e) {
						Connection con = DriverManager.getConnection(url, uname, pwd);
						Statement statement = con.createStatement(); statement.execute(query);
					}
				} catch (SQLSyntaxErrorException e) {
					System.out.println("Invalid Syntax! Try Again!");
					System.out.println("See report?");
					cmd = sc.nextLine();
					if (cmd.contains("y")) e.printStackTrace();
				}
			} catch (SQLException e) {
				System.out.println("Something went wrong! Try Again!");
				System.out.println("See report?");
				cmd = sc.nextLine();
				if (cmd.contains("y")) e.printStackTrace();
			}
			
		}
		sc.close();
		System.out.println("Goodbye!");
	}

}
