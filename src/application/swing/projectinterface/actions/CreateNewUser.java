package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import application.swing.main.Main;

public class CreateNewUser implements ActionListener {

	private JTextField unameField;
	private JPasswordField pwdField;
	private JPasswordField confPwdField;
	
	public CreateNewUser(JTextField uN, JPasswordField pw, JPasswordField cpw) {
		unameField = uN;
		pwdField = pw;
		confPwdField = cpw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (Main.userDB.verifyUserExists(unameField.getText())) {
			System.out.println("USER ALREADY EXISTS!");
			return;
		}
		String pwd = new String(pwdField.getPassword());
		String pwdc = new String(confPwdField.getPassword());
		if (!pwd.equals(pwdc)) {
			System.out.println("PASSWORDS DONT MATCH");
			return;
		}
		Main.userDB.createNewUser(unameField.getText(), new String(pwdField.getPassword()));
		Main.userDB.login(unameField.getText());
	}

}
