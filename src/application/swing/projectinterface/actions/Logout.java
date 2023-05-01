package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.swing.logininterface.components.LoginPanel;
import application.swing.main.Main;
import application.swing.projectinterface.util.Phase;

public class Logout implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.userDB.logout();
		Main.mainPanel.remove(Main.treeSelectionPanel);
		Main.treeSelectionPanel = null;
		Main.currentPhase = Phase.LOGIN;
		Main.mainPanel.setActivePanel(new LoginPanel());
	}
	
}
