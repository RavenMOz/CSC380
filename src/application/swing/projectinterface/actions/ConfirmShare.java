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
			SQLCommands.share(Main.activeFamily, SQLCommands.getUserID(unameField.getText()));
			Main.mainPanel.remove(pane);
			Main.mainPanel.remove(pane.overlay);
			Main.globNav.clear.setEnabled(true);
			Main.globNav.close.setEnabled(true);
			Main.globNav.share.setEnabled(true);
			Main.globNav.export.setEnabled(true);
			Main.globNav.save.setEnabled(true);
			SharePane.sharebutton = null;
		}
	}
	
	

}
