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

public class DeleteTree implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SQLCommands.deleteFamily(Main.familyList.getSelectedValue());
		Vector<Family> listVector = new Vector<>(SQLCommands.getFamilies(UserDB.activeUser));
		Main.familyList = new JList<>(listVector);
		Main.mainPanel.remove(Main.treeSelectionPanel);
		Main.treeSelectionPanel = new TreeSelectionPanel();
		Main.mainPanel.setActivePanel(Main.treeSelectionPanel);
	}

}
