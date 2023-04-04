package application.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.data.Member;

public class SQLCommands {
	
	static final String url = "jdbc:mysql://localhost:3306/familytree";
	static final String uname = "testuser";
	static final String pwd = "123";
	
	public static Member getMemberByID(long memberID) throws SQLException {
		
		Member m = null;
		
		String query = "SELECT * FROM Members WHERE memberID = " + memberID + ";";
		Connection con = DriverManager.getConnection(url, uname, pwd);
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(query);
		
		long mID = 0;
		String name = "";
		String bio = "";
		int bDay = 0;
		int bMonth = 0;
		int bYear = 0;
		int children = 0;
		int mother = 0;
		int father = 0;
		int spouse = 0;
		
		int c = 0;
		
		while (result.next()) {
			String famdata = "";
			for (int i = 1; i <= 10; i++) {
				famdata = result.getString(i);
				if (i==1) mID = Long.parseLong(famdata);
				if (i==2) name = famdata;
				if (i==3) bio = famdata;
				if (i==4) bDay = Integer.parseInt(famdata);
				if (i==5) bMonth = Integer.parseInt(famdata);
				if (i==6) bYear = Integer.parseInt(famdata);
				if (i==7) children = Integer.parseInt(famdata);
				if (i==8) mother = Integer.parseInt(famdata);
				if (i==9) father = Integer.parseInt(famdata);
				if (i==10) spouse = Integer.parseInt(famdata);
				
			}
			m = new Member(mID, name, bio, bDay, bMonth, bYear, children, mother, father, spouse, 0);
			c++;
		} 
		if (c == 0) return null;
		else return m;
	}

}
