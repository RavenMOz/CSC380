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
import java.util.List;

import application.data.Family;
import application.data.Member;

public class SQLCommands {
	
	static final String url = "jdbc:mysql://localhost:3306/familytree";
	static final String uname = "testuser";
	static final String pwd = "123";
	static Connection con = getConnection(); 
	
	public static Member getMemberByID(long memberID) {
				
		String query = "SELECT * FROM Members WHERE memberID = " + memberID + ";";
		return constructMember(query);
		
	}
//	public static Member constructSpouse(Member m) {
//		
//	}
	private static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, uname, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	private static Member constructMember(String query) {
		
		try {
			return constructMembers(query).get(0);
		} catch (Exception e) {
			return null;
		}
		
	}
	private static ArrayList<Member> constructMembers(String query) {
		
		try {
			ArrayList<Member> mems = new ArrayList<>();
			
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
					if (i==3) name = famdata/*.replace("''", "'")*/;
					if (i==4) bio = famdata/*.replace("''", "'")*/;
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
		
		if (!isWritten(fam)) {
			String query = "insert into Families values ( " + fam.getFamilyID() + ", " + fam.getOwnerID() + ")";
			send(query);
		}
		
	}
	public static Family readFamily(long fID) { return new Family(fID); }
	private static void updateMember(Member m) {
		
		LocalDate d = LocalDate.of(m.getBYear(), m.getBMonth(), m.getBDay());
		Date date = Date.valueOf(d);
		
		String query = "UPDATE Members SET"
				+ " name = '" + m.getName()
				+ "', bio = '" + m.getBio()
				+ "', bDate = '" + new SimpleDateFormat("yyyy-MM-dd").format(date) + "'";
		
		if (m.getParentOne() != null) query += ", pone = " + m.getParentOne().getMemberID();
		if (m.getParentTwo() != null) query += ", ptwo = " + m.getParentTwo().getMemberID();
		if (m.getSpouse() != null) query += ", spouse = " + m.getSpouse().getMemberID();

		query += " WHERE memberID = " + m.getMemberID() + "";
		
		send(query);
		
		rewriteChildren(m);
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
		
		rewriteChildren(m);
		
	}
	private static void rewriteChildren(Member m) {
		
		if (m.getChildren() == null) return;
		if (m.getChildren().size() == 0) {
			clearChildren(m); return;
		}
		
		clearChildren(m);
		
		for (Member c : m.getChildren()) {
			long cid = c.getMemberID();
			long p1id = 0;
			long p2id = 0;
			
			if (c.getParentOne() != null) p1id = c.getParentOne().getMemberID();
			if (c.getParentTwo() != null) p2id = c.getParentTwo().getMemberID();
			
			String query = "INSERT INTO Children VALUES (" + cid + ", " + p1id + ", " + p2id + ")";
			send(query);
		}
	}
	private static void clearChildren(Member m) {
		String query = "delete from Children where poneID = " + m.getMemberID();
		send(query);
		query = "delete from Children where ptwoID = " + m.getMemberID();
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
	public static long getOwnerID(long famID) {
		long id = 0;
		String query = "select * from userFamilies where familyID = " + famID;
		
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				String data = "";
				data = result.getString(1);
				id = Long.parseLong(data);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		return id;
		
	}
	public static void deleteFamily(long familyID) {
		String query = "delete from Members where familyID = " + familyID;
		send(query);
		query = "delete from Families where familyID = " + familyID;
		send(query);
		query = "delete from Children where familyID = " + familyID;
	}
	public static void deleteFamily(Family fam) {
		long familyID = fam.getFamilyID();
		String query = "delete from Members where familyID = " + familyID;
		send(query);
		query = "delete from Families where familyID = " + familyID;
		send(query);
		query = "delete from Children where familyID = " + familyID;
	}
	public static List<Family> getFamilies(long userID) {
		
		List<Family> fams = new ArrayList<>();
		
		for (long l : getFamIDsByUser(userID)) {
			fams.add(readFamily(l));
		}
		
		return fams;
		
	}
	private static List<Long> getFamIDsByUser(long userID) {
		
		List<Long> famIDs = new ArrayList<>();
		
		String query = "select * from userFamilies where userID = " + userID;
		
		Statement statement;
		
		try {
			statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				String data = "";
				data = result.getString(1);
				famIDs.add(Long.parseLong(data));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Long>();
		}
		return famIDs;
	}
	public static void setRelations(Family f) {
		for (Member m : f.getMembers()) {
			Member spouse = f.getMember(getSpouseID(m));
			ArrayList<Member> children = getChildren(m, f);
			if (spouse != null) m.setSpouse(spouse);
			m.setChildren(children);
			Member pone = f.getMember(getParentOneID(m));
			Member ptwo = f.getMember(getParentTwoID(m));
			if (pone != null) m.setParentOne(pone);
			if (ptwo != null) m.setParentTwo(ptwo);
		}
	}
	private static long getParentTwoID(Member child) {
		
		String query = "select ptwoID from children where familyID = " + 
				child.getFamilyID() + " and memberID = " + child.getMemberID();
		
		long id = 0;
		
		Statement statement;
		
		try {
			statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				String data = "";
				data = result.getString(1);
				id = Long.parseLong(data);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		return id;
	}
	private static long getParentOneID(Member child) {
		
		String query = "select poneID from children where familyID = " + 
				child.getFamilyID() + " and memberID = " + child.getMemberID();
		
		long id = 0;
		
		Statement statement;
		
		try {
			statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				String data = "";
				data = result.getString(1);
				id = Long.parseLong(data);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		return id;
	}
	private static ArrayList<Member> getChildren(Member m, Family f) {
		
		String query = "select memberID from children where poneID = " + m.getMemberID() + 
					   " or ptwoID = " + m.getMemberID();
		
		Statement statement;
		ArrayList<Member> children = new ArrayList<>();
		
		try {
			statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				String data = "";
				data = result.getString(1);
				children.add(f.getMember(Long.parseLong(data)));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Member>();
		}
		
		return children;
	}
//	private static boolean hasChildren(Member m) {
//		String query = "select from children where poneID = " + m.getMemberID() + 
//					   " or ptwoID = " + m.getMemberID();
//		Statement statement; int ccount = 0;
//		try {
//			statement = con.createStatement();
//			ResultSet result = statement.executeQuery(query);
//			while (result.next()) {
//				ccount++;
//			} 
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return (ccount > 0);
//	}
	private static long getSpouseID(Member otherSpouse) {
		
		String query = "select memberID from members where familyID = " + 
		otherSpouse.getFamilyID() + " and spouse = " + otherSpouse.getMemberID();
		
		long id = 0;
		
		Statement statement;
		
		try {
			statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				String data = "";
				data = result.getString(1);
				id = Long.parseLong(data);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		return id;
	}
}
