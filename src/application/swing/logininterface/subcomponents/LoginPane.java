package application.swing.logininterface.subcomponents;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import application.swing.main.Main;
import application.swing.projectinterface.actions.BackToLogin;
import application.swing.projectinterface.actions.CreateNewUser;
import application.swing.projectinterface.actions.Login;
import application.swing.projectinterface.actions.NewUserPrompt;
import application.swing.projectinterface.subcomponents.Button;
import application.swing.projectinterface.util.SubPhase;

public class LoginPane extends JPanel{

	private static final long serialVersionUID = -8782310541127583212L;

	Thread lpaneThread;
	public Button login;
	
	JLabel username;
	JTextField unameField;
	JLabel pwd;
	JLabel pwdconfirm;
	JPasswordField pwf;
	JPasswordField pwfconfirm;
	Button newUser;
	Button back;
	Button createUser;
	JLabel loading;
	
	SubPhase phase;
	
	public LoginPane() {
		
		super();
		setLayout(null);
		
		setLoginFields();
		
		Main.loginPane = this;
		
	}

	public void setLoginFields() {
		
		removeAll();
		phase = SubPhase.LOGIN;
		
		username = new JLabel("Username:");
			
		unameField = new JTextField(30);
				
		pwd = new JLabel("Password:");
		
		pwf = new JPasswordField(30);
		pwf.setEchoChar('*');
		
		login = new Button("Login", new Login(unameField, pwf));
		newUser = new Button("New User", new NewUserPrompt());
		
		unameField.addActionListener(new Login(unameField, pwf));
		pwf.addActionListener(new Login(unameField, pwf));
		
		add(username);
		add(unameField);
		add(pwd);
		add(pwf);
		add(login);
		add(newUser);
		
		repaint();
		resize();

	}
	
	public void setLoadingFields() {
		
		removeAll();
		phase = SubPhase.LOADING;
		loading = new JLabel("Loading...");
		loading.setFont(new Font("Lexend", Font.BOLD, 26));
		add(loading);
		resize();
		
		
	}
	
	public void setNewUserFields() {
		
		removeAll();
		phase = SubPhase.NEWUSER;
		
		username = new JLabel("Username:");
		
		unameField = new JTextField(30);
				
		pwd = new JLabel("Password:");
		
		pwf = new JPasswordField(30);
		pwf.setEchoChar('*');
		
		pwdconfirm = new JLabel("Confirm Password:");
		
		pwfconfirm = new JPasswordField(30);
		pwfconfirm.setEchoChar('*');
		
		back = new Button("< Back", new BackToLogin());
		createUser = new Button("Create", new CreateNewUser(unameField, pwf, pwfconfirm));
		
		add(username);
		add(unameField);
		add(pwd);
		add(pwf);
		add(pwdconfirm);
		add(pwfconfirm);
		add(back);
		add(createUser);
		
		repaint();
		resize();

		
	}
	
	public void resize() {
		
		if (phase == SubPhase.NEWUSER) {
			Component[] group1 = {username, unameField};
			Component[] group2 = {pwd, pwf};
			Component[] group3 = {pwdconfirm, pwfconfirm};
			Component[] group4 = {back, createUser};
			
			groupCenter(50,10,group1);
			groupCenter(100,10,group2);
			groupCenter(150,10,group3);
			groupCenter(200,10,group4);
		} else if (phase == SubPhase.LOGIN){
			Component[] group1 = {username, unameField};
			Component[] group2 = {pwd, pwf};
			Component[] group3 = {login, newUser};
			
			groupCenter(50,10,group1);
			groupCenter(100,10,group2);
			groupCenter(150,10,group3);
		} else {
			Component[] group1 = {loading};
			
			groupCenter(getHeight()/2, 0, group1);
		}
		
		

	}
	
	void groupCenter(int y, int space, Component[] cs) {
		int x = (getWidth()/2) - (space * (cs.length-1));
		for (Component c : cs) {
			x -= (c.getWidth()/2);
		}
		for (Component c : cs) {
			c.setBounds(x,y,(int)c.getPreferredSize().getWidth(),(int)c.getPreferredSize().getHeight());
			x += c.getWidth(); x += space;
		}
		
	}

	//private boolean isProperPhase() { return (Main.currentPhase.equals(Phase.LOGIN)); }

	public void enter() {
		login.doClick();
	}
	
}
