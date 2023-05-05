package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import application.swing.main.Main;

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
		boolean allow = Main.userDB.verifyUserExists(uName) &&
				Main.userDB.verifyPassword(uName, password);
		if (allow) {
			Main.loginPane.setLoadingFields();
			Main.userDB.login(uName);
		} else {

		}
	}
	
}
