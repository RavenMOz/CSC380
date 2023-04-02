package application.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

	public static void main(String[] args) throws SQLException {
		
		String url = "jdbc:mysql://localhost:3306/familytree";
		String uname = "testuser";
		String pwd = "123";
		String query = "select * from Members";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection con = DriverManager.getConnection(url, uname, pwd);
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				String famdata = "";
				for (int i = 1; i <= 10; i++) {
					famdata += result.getString(i) + "; ";
				}
				System.out.println(famdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
