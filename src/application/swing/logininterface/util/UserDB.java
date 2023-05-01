package application.swing.logininterface.util;

import java.util.Map;
import java.util.Vector;

import javax.swing.JList;

import application.data.Family;
import application.middleware.JSONWriteFunctions;
import application.storage.SQLCommands;
import application.swing.logininterface.components.TreeSelectionPanel;
import application.swing.main.Main;
import application.swing.projectinterface.util.Phase;

public class UserDB {

	private static Map<String, Object> users;
	private static Map<String, Object> userIDs;
	
	public static long activeUser;
	public static String activeUserName;
	
	public UserDB() {
		users = JSONWriteFunctions.getUserMap();
		userIDs = JSONWriteFunctions.getUserIDMap();
		activeUser = 0;
		activeUserName = "";
	}
	public boolean verifyUserExists(String uname) {
		if (users.containsKey(uname)) return true;
		else return false;
	}
	public boolean verifyPassword(String uname, String pwd) {
		if (verifyUserExists(uname) && 
			users.get(uname) != null &&
			((String)users.get(uname)).equals(pwd)) return true;
		else return false;
	}
	public void login(String uname) {
		if (userIDs.containsKey(uname) &&
			userIDs.get(uname) != null) {
			
			activeUser = Long.parseLong((String)userIDs.get(uname));
			System.out.println(activeUser);
			activeUserName = uname;
			
			Vector<Family> listVector = new Vector<>(SQLCommands.getFamilies(UserDB.activeUser));
			Main.familyList = new JList<>(listVector);
			Main.mainPanel.remove(Main.loginPanel);
			Main.loginPanel = null;
			Main.currentPhase = Phase.TREESELECT;
			Main.treeSelectionPanel = new TreeSelectionPanel();
			Main.mainPanel.setActivePanel(Main.treeSelectionPanel);
			
		}
	}
	private long generateID() {
		return System.currentTimeMillis() + 
				(int)(Math.random() * 100);
	}
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	public void createNewUser(String uname, String pwd) {
		users = JSONWriteFunctions.getUserMap();
		users.put(uname, pwd);
		JSONWriteFunctions.writeUserMap(users);
		userIDs = JSONWriteFunctions.getUserIDMap();
		userIDs.put(uname, generateID()+"");
		JSONWriteFunctions.writeUserIDMap(userIDs);
	}
	public long getUserID(String uname) {
		userIDs = JSONWriteFunctions.getUserIDMap();
		return Long.parseLong((String)userIDs.get(uname));
	}
	
}
