package application.storage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import application.data.Family;
import application.data.Member;

public class SQLCommands {
	
	static final String url = "jdbc:mysql://localhost:3306/familytree";
	static final String uname = "testuser";
	static final String pwd = "123";
	static public ArrayList<Family> activeFamilies = new ArrayList<>();
	
	public static Member getMemberByID(long memberID) {
				
		String query = "SELECT * FROM Members WHERE memberID = " + memberID + ";";
		return constructMember(query);
		
	}
	private static Member constructMember(String query) {
		
		try {
			return constructMembers(query).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		
	}
	private static ArrayList<Member> constructMembers(String query) {
		
		try {
			ArrayList<Member> mems = new ArrayList<>();
			Connection con = DriverManager.getConnection(url, uname, pwd);
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			long mID = 0;
			long fID = 0;
			String name = "";
			String bio = "";
			LocalDate bDate = null;
			long p1 = 0;
			long p2 = 0;
			long spouse = 0;
					
			while (result.next()) {
				String famdata = "";
				for (int i = 1; i <= 8; i++) {
					famdata = result.getString(i);
					if (i==1) mID = Long.parseLong(famdata);
					if (i==2) fID = Long.parseLong(famdata);
					if (i==3) name = famdata;
					if (i==4) bio = famdata;
					if (i==5) bDate = Date.valueOf(famdata).toLocalDate();
					if (i==6) spouse = Long.parseLong(famdata);
					if (i==7) p1 = Long.parseLong(famdata);
					if (i==8) p2 = Long.parseLong(famdata);
					
				}
				
				mems.add(new Member(mID, fID, name, bio, 
						bDate.getDayOfMonth(), bDate.getMonthValue(), bDate.getYear(),
						p1, p2, spouse));
			} 
			return mems;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Member>();
		}
		
		
		
		
	}
	public static ArrayList<Member> getMembersByFamID(long famID) {
		
		String query = "select * from Members where familyID = " + famID;
		
		return constructMembers(query);
		
	}
	public static ArrayList<Member> getChildrenByParentID(long parentID) {
		
		String query = "select * from Children where poneID = " + parentID + " or ptwoID = " + parentID;
		
		return constructMembers(query);

	}
	public static void writeFamily(Family fam) {
		
		for (Member m : fam.getMembers()) {
			if (isWritten(m)) updateMember(m);
			else writeMember(m);
		}
		
	}
	public static Family readFamily(long fID) { return new Family(fID); }
	private static void updateMember(Member m) {
		
		LocalDate d = LocalDate.of(m.getBYear(), m.getBMonth(), m.getBDay());
		Date date = Date.valueOf(d);
		
		String query = "UPDATE Members SET"
				+ ", name = '" + m.getName()
				+ "', bio = '" + m.getBio()
				+ "', bDate = '" + new SimpleDateFormat("yyyy-MM-dd").format(date) + "'";
		
		if (m.getParentOne() != null) query += ", pone = " + m.getParentOne().getMemberID();
		if (m.getParentTwo() != null) query += ", ptwo = " + m.getParentTwo().getMemberID();
		if (m.getSpouse() != null) query += ", spouse = " + m.getSpouse().getMemberID();

		query += " WHERE memberID = " + m.getMemberID() + "";
		
		send(query);
	}
	static void writeMember(Member m) {
		
		long mid = m.getMemberID();
		long fid = m.getFamilyID();
		String name = m.getName();
		String bio = m.getBio();
		LocalDate d = LocalDate.of(m.getBYear(), m.getBMonth(), m.getBDay());
		Date date = Date.valueOf(d);
		
		long pone = 0;
		long ptwo = 0;
		long spouse = 0;
		
		if (m.getSpouse() != null) spouse = m.getSpouse().getMemberID();
		if (m.getParentOne() != null) pone = m.getParentOne().getMemberID();
		if (m.getParentTwo() != null) ptwo = m.getParentTwo().getMemberID();
		
		String query = "INSERT INTO Members VALUES (" + mid + ", " + fid + ", '" + name + "', '" + bio + "', '" + new SimpleDateFormat("yyyy-MM-dd").format(date) + 
				"', " + spouse + ", " + pone + ", " + ptwo + ");";
		send(query);
		
	}
	private static void send(String query) {
		
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
	public static boolean isWritten(Member m) {
		if (getMemberByID(m.getMemberID()) != null) return true;
		else return false;
	}
	public static boolean isWritten(Family f) {
		if (getMembersByFamID(f.getFamilyID()).size() == f.getSize()) return true;
		else return false;
	}
}
