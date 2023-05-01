package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLayeredPane;

import application.swing.main.Main;
import application.swing.projectinterface.subcomponents.OverlayPane;
import application.swing.projectinterface.util.OvalButton;

public class OpenPane implements ActionListener {
		
	@Override
	public void actionPerformed(ActionEvent e) {

		for (OvalButton b : Main.globNav.buttons) {
			b.setEnabled(false);
		}
		
		Main.memEditor.bioFieldScroller.setEnabled(false);
		Main.memEditor.nameField.setEnabled(false);
		
		Main.mainPanel.add(new OverlayPane(), JLayeredPane.POPUP_LAYER);
				
		
	}
	
}
