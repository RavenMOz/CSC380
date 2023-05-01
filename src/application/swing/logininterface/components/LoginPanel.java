package application.swing.logininterface.components;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import application.swing.logininterface.subcomponents.LoginPane;
import application.swing.main.Main;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 3420645465731902041L;
	JPanel subPanel;
	
	Thread lplThread;
	
	public LoginPanel() {
		super();
		
		Main.loginPane = new LoginPane();
		setLayout(null);
		
		subPanel = Main.loginPane;
		setBackground(Color.pink);
		
		add(subPanel);
		Main.loginPanel = this;
			
	}
	
	public void setSubPanel(JPanel newSubPanel) {
		remove(subPanel);
		subPanel = newSubPanel;
		add(subPanel, BorderLayout.CENTER);
	}
	
	public void resize() {
		setBounds(0,0,Main.window.getWidth(), Main.window.getHeight());
		int width = (int)(Main.window.getWidth() * (3.0/5.0));
		if (width > 500) width = 500;
		subPanel.setBounds(Main.window.getWidth()/2 - width/2,
						   Main.window.getHeight()/8,
						   width,
						   (int)(Main.window.getHeight() * (5.5/8.0)));
	}
	
}
