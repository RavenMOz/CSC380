package application.swing.keyhandlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import application.swing.logininterface.subcomponents.LoginPane;

public class LoginPaneKeyHandler implements KeyListener {

	LoginPane loginPane;
	
	public LoginPaneKeyHandler(LoginPane pane) {
		loginPane = pane;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) loginPane.login.doClick();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
