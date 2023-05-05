package application.swing.logininterface.util;

import java.util.Vector;

import javax.swing.JList;

import application.data.Family;
import application.storage.SQLCommands;
import application.swing.logininterface.components.TreeSelectionPanel;
import application.swing.main.Main;
import application.swing.projectinterface.util.Phase;

public class UserDB {
	
	public static long activeUser;
	public static String activeUserName;
	
	public UserDB() {
		activeUser = 0;
		activeUserName = "";
	}
	public boolean verifyUserExists(String uname) {
		return SQLCommands.userExists(uname);
	}
	public boolean verifyPassword(String uname, String pwd) {
		return SQLCommands.checkPassword(uname, pwd);
	}
	public void login(String uname) {
		
		activeUser = SQLCommands.getUserID(uname);
		activeUserName = uname;
			
		Vector<Family> listVector = new Vector<>(SQLCommands.getFamilies(UserDB.activeUser));
		Main.familyList = new JList<>(listVector);
		Main.mainPanel.remove(Main.loginPanel);
		Main.loginPanel = null;
		Main.currentPhase = Phase.TREESELECT;
		Main.treeSelectionPanel = new TreeSelectionPanel();
		Main.mainPanel.setActivePanel(Main.treeSelectionPanel);
			
	}
	public static long generateID() {
		return System.currentTimeMillis() + 
				(int)(Math.random() * 100);
	}
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	public void createNewUser(String uname, String pwd) {
		SQLCommands.createUser(uname, pwd);
	}
//	public long getUserID(String uname) {
//		userIDs = JSONWriteFunctions.getUserIDMap();
//		return Long.parseLong((String)userIDs.get(uname));
//	}
	
}
