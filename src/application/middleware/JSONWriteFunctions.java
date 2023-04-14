package application.middleware;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import application.data.Family;
import application.data.Member;
import application.storage.SQLCommands;

public class JSONWriteFunctions {
	
	public final static String mainDirectory = "/home/FamilyTree/files/userData/";
	public final static String testDirectory = "C:\\Users\\samth\\OneDrive\\Desktop\\Files & Zips\\Programming\\380\\JsonTest";
	public final static String sep = System.getProperty("file.separator");
	public final static long testUID = 694201337;

	public static void main(String[] args) {
		createUserData(testUID);
	}
	
	public static void createUserData(long userID) {
		
		if (userID == 0) return;
		
		String dir;
		if (sep.contains("/")) { dir = mainDirectory; }
		else { dir = testDirectory; }
		
		dir = dir + sep + userID + sep;
		
		new File(dir).mkdirs();
		
		for (Family f : SQLCommands.getFamilies(userID)) {
			createFamily(f, dir);
		}
		
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
