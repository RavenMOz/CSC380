package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JList;

import application.data.Family;
import application.storage.SQLCommands;
import application.swing.logininterface.components.TreeSelectionPanel;
import application.swing.logininterface.util.UserDB;
import application.swing.main.Main;
import application.swing.projectinterface.util.Phase;

public class Close implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.activeMember = null;
		Main.activeFamily = null;
		Main.mainPanel.remove(Main.tei);
		Main.tei = null;
		Main.currentPhase = Phase.TREESELECT;
		Vector<Family> listVector = new Vector<>(SQLCommands.getFamilies(UserDB.activeUser));
		Main.familyList = new JList<>(listVector);
		Main.treeSelectionPanel = new TreeSelectionPanel();
		Main.mainPanel.setActivePanel(Main.treeSelectionPanel);
		
		
	}
	
}
