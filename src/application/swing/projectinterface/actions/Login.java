package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import application.data.Family;
import application.storage.SQLCommands;
import application.swing.logininterface.components.TreeSelectionPanel;
import application.swing.logininterface.util.UserDB;
import application.swing.main.Main;
import application.swing.projectinterface.util.Phase;

public class Login implements ActionListener {

	private JTextField unameField;
	private JPasswordField pwdField;
	
	public Login(JTextField uN, JPasswordField pw) {
		unameField = uN;
		pwdField = pw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String uName = unameField.getText();
		String password = new String(pwdField.getPassword());
		if (Main.userDB.verifyUserExists(uName) &&
			Main.userDB.verifyPassword(uName, password)) {
			Main.loginPane.setLoadingFields();
			Main.userDB.login(uName);
		} else {

		}
	}
	
}
