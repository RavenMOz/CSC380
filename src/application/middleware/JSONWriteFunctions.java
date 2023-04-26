package application.middleware;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import application.data.Family;
import application.data.Member;
import application.storage.SQLCommands;

public class JSONWriteFunctions {
	
	public static String mainDirectory = "/home/FamilyTree/files/userData/";
	public static String sep = System.getProperty("file.separator");
	public static long testUID = 694201337;

	public static void createUserData(long userID) {
		
		if (userID == 0) return;
		
		String dir = mainDirectory;
		
		dir = dir + userID + sep;
		
		new File(dir).mkdirs();
		
		for (Family f : SQLCommands.getFamilies(userID)) {
			createFamily(f, dir);
		}
		
	}
	
	public static void dispose(long userID) {
		File userStuff = new File(mainDirectory + userID + sep);
		deleteDirectory(userStuff);
	}
	
	static boolean deleteDirectory(File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
	
	public static void save(long userID) {
		File folder = new File(mainDirectory + userID + sep).listFiles()[0];
		long famID = Long.parseLong(folder.getName());
		Family f = readFamily(userID, famID);
		SQLCommands.writeFamily(f);
	}
	
	private static Family readFamily(long userID, long famID) {
		File dir = new File(mainDirectory + userID + sep + famID + sep);
		ArrayList<Member> members = new ArrayList<>();
		for (File f : dir.listFiles()) {
			if (f != null) {
				long mID = Long.parseLong(f.getName().substring(0,13));
				
				try {
					String jsonString;
					jsonString = getJsonAsString(f);
					members.add(new Member(new JSONObject(jsonString), mID, famID));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		for (Member m : members) {
			if (getSpouse(m, members) != null) m.setSpouse(getSpouse(m, members));
			if (getP1(m, members) != null) m.setParentOne(getP1(m, members));
			if (getP2(m, members) != null) m.setParentTwo(getP2(m, members));
			m.setChildren(getChildren(m, members));
		}
		
		return new Family(members, famID, userID);
	}
	private static String getJsonAsString(File f) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String json = "";
		while (reader.ready()) {
			json += reader.readLine();
		}
		reader.close();
		return json;
	}

	private static ArrayList<Member> getChildren(Member m, ArrayList<Member> members) {
		ArrayList<Member> childs = new ArrayList<>();
		for (Member other : members) {
			if (other.poneID == m.memberID || other.ptwoID == m.memberID) childs.add(other);
		}
		return childs;
	}
	private static Member getP1(Member m, ArrayList<Member> members) {
		for (Member other : members) {
			if (other.memberID == m.poneID) return other;
		}
		return null;
	}
	private static Member getP2(Member m, ArrayList<Member> members) {
		for (Member other : members) {
			if (other.memberID == m.ptwoID) return other;
		}		
		return null;
	}
	private static Member getSpouse(Member m, ArrayList<Member> members) {
		for (Member other : members) {
			if (other.memberID == m.spouseID) return other;
		}
		return null;
	}
	public static void createFamily(Family f, String dir) {
		
		if (f == null) return;
		
		String newDir = dir + f.getFamilyID() + sep;
		
		new File(newDir).mkdirs();
		
		for (Member m : f.getMembers()) {
			createMember(m, newDir);
		}
		
	}
	
	public static void createMember(Member m, String dir) {
				
		if (m == null) return;
				
		JsonObject obj = new JsonObject();
		String newDir = dir + m.getMemberID() + ".json";
		
		long spouseID = 0;
		long parentOneID = 0;
		long parentTwoID = 0;
		
		if (m.getSpouse() != null) spouseID = m.getSpouse().getMemberID();
		if (m.getParentOne() != null) parentOneID = m.getParentOne().getMemberID();
		if (m.getParentTwo() != null) parentTwoID = m.getParentTwo().getMemberID();
		
		obj.addProperty("name", m.getName());
		obj.addProperty("bio", m.getBio());
		obj.addProperty("birthDay", m.getBDay());
		obj.addProperty("birthMonth", m.getBMonth());
		obj.addProperty("birthYear", m.getBYear());
		obj.addProperty("spouse", spouseID);
		obj.addProperty("parentOne", parentOneID);
		obj.addProperty("parentTwo", parentTwoID);
		
		JsonArray children = new JsonArray();

		for (Member child : m.getChildren()) {
			children.add(child.getMemberID());
		}
		
		obj.add("children", children);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(newDir);
			gson.toJson(obj, writer);
			writer.flush();
			writer.close();
		} catch (JsonIOException | IOException e) {
			System.out.println("ERROR WRITING JSON! SEE REPORT BELOW!");
			e.printStackTrace();
			System.out.println("END OF REPORT");
		} 
	}
	
}
