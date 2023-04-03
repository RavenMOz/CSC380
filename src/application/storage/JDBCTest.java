package application.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.data.Member;

public class JDBCTest {

	static final String url = "jdbc:mysql://localhost:3306/familytree";
	static final String uname = "testuser";
	static final String pwd = "123";
	static long micahID;
	static Member[] membs;
	
	static String bars = "\n-------------------\n";
	
	public static void main(String[] args) throws SQLException {
		
		long startTime = System.currentTimeMillis();
		
		System.out.println(bars +
				   "Starting Test..." +
				   bars);
		
		clearMembers();
		getMembers();
		insertMembers();
		getMembers();
		getMemberByID(micahID);
		constructMembers();
		editMembers();
		getMembers();
		clearMembers();
		getMembers();
		
		long endTime = System.currentTimeMillis();
		long timetaken = endTime-startTime;
		
		System.out.println(bars + 
					"Done! Time Taken: " + timetaken + "ms" +
					bars);
		
	}
	
	static void constructMembers() throws SQLException {
		
		System.out.println(bars +
				   "Converting from MySQL to Java Objects..." +
				   bars);
		
		Member[] mems = constructMemberArray();
		
		for (Member m : mems) {
			System.out.println(m.toString());
		}
		
	}
	
	static Member[] constructMemberArray() throws SQLException {
		Member[] mems = new Member[5];
		
		String query = "select * from Members";
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
			mems[c] = new Member(mID, name, bio, bDay, bMonth, bYear, children, mother, father, spouse);
			c++;
		} 
		if (c == 0) System.out.println("No members found!");
		membs = mems;
		return mems;
	}
	
	static void getMemberByID(long mID) throws SQLException {
		
		System.out.println(bars +
				"Finding a specific member with ID " + mID + "..." +
				   bars);
		
		String query = "SELECT * FROM Members WHERE memberID = " + mID + ";";
		Connection con = DriverManager.getConnection(url, uname, pwd);
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(query);
		
		int c = 0;
		while (result.next()) {
			String famdata = "";
			for (int i = 1; i <= 10; i++) {
				famdata += result.getString(i) + "; ";
			}
			System.out.println(famdata);
			c++;
		} 
		if (c == 0) System.out.println("No members found!");
	}
	
	static void insertMembers() {
		
		System.out.println(bars +
						   "Inserting Members..." +
						   bars);
		
		for (Member m : getMembersArray() ) {
			insertMember(m);
		}
		
		System.out.println("\nTest Members Added!");
		
	}
	
	static Member[] getMembersArray() {
		Member[] mems = new Member[5];
		mems[0] = new Member("John Marston", "Scarfaced outlaw who never learned to swim.");
		mems[1] = new Member("Micah Bell", "A survivor with anger issues and trendy facial hair.");
		mems[2] = new Member("Dutch Van Der Linde", "Always has a plan, usually involving a train.");
		mems[3] = new Member("Arthur Morgan", "The man with the black lung.");
		mems[4] = new Member("Marion \"Bill\" Williamson", "Disgraced cavalryman who is as dangerous as he is dumb.");
		
		micahID = mems[1].memberID;
		
		return mems;
	}
	
	static void clearMembers() { 
		
		System.out.println(bars +
				   "Clearing Members..." +
				   bars);
		
		send("DELETE FROM Members"); 
		System.out.println("Members Cleared!");}
	
	static void getMembers() throws SQLException {
		
		System.out.println(bars +
				   "Fetching Stored Members..." +
				   bars);
		
		String query = "select * from Members";
		Connection con = DriverManager.getConnection(url, uname, pwd);
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(query);
		
		int c = 0;
		while (result.next()) {
			String famdata = "";
			for (int i = 1; i <= 10; i++) {
				famdata += result.getString(i) + "; ";
			}
			System.out.println(famdata);
			c++;
		} 
		if (c == 0) System.out.println("No members found!");
	}
	
	static void editMembers() {
		
		System.out.println(bars +
				   "Editing & Rewriting Members..." +
				   bars);
		
		for (Member m : membs) {
			m.setName("John Doe");
			update(m);
		}
	}
	
	static void update(Member m) {
		System.out.println(m.getName());
		String query = "UPDATE Members SET"
				+ " name = '" + m.getName() + "',"
				+ " biography = '" + m.getBio() + "',"
				+ " bDay = " + m.getBDay() + ","
				+ " bMonth = " + m.getBMonth() + ","
				+ " bYear = " + m.getBYear()
//				+ "children = " + m.getChildren() + ","
//				+ "mother = " + m.getName() + ","
//				+ "father = " + m.getName() + ","
//				+ "spouse = " + m.getName() + ","
				+ " WHERE memberID = " + m.memberID + "";
		send(query);
	}
	
	static void insertMember(Member m) {
		
		long mid = m.memberID;
		String name = m.getName();
		String bio = m.getBio();
		int bDay = m.getBDay();
		int bMonth = m.getBMonth();
		int bYear = m.getBYear();
		int children = 0;
		int mother = 0;
		int father = 0;
		int spouse = 0;
		
		String query = "INSERT INTO Members VALUES (" + mid + ", '" + name + "', '" + bio + "', " + bDay + 
				", " + bMonth + ", " + bYear + ", " + children + ", " + mother + ", " + father + ", " + spouse + ");";
		System.out.println(query);
		send(query);
		
	}
	
	static void send(String query) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection con = DriverManager.getConnection(url, uname, pwd);
			Statement statement = con.createStatement(); statement.execute(query);
//			ResultSet result = statement.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
