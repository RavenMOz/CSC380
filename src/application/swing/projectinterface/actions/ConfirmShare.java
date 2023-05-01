package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import application.storage.SQLCommands;
import application.swing.main.Main;
import application.swing.projectinterface.subcomponents.SharePane;

public class ConfirmShare implements ActionListener {

	JTextField unameField;
	SharePane pane;
	
	public ConfirmShare(JTextField unf, SharePane sharePane) {
		unameField = unf;
		pane = sharePane;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (Main.userDB.verifyUserExists(unameField.getText())) {
			SQLCommands.share(Main.activeFamily, Main.userDB.getUserID(unameField.getText()));
			Main.mainPanel.remove(pane);
			Main.mainPanel.remove(pane.overlay);
		}
	}
	
	

}
