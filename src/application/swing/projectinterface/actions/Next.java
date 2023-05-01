package application.swing.projectinterface.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.swing.main.Main;
import application.swing.projectinterface.subcomponents.OverlayPane;
import application.swing.projectinterface.util.OvalButton;

public class Next implements ActionListener {
	
	OverlayPane pane;
	public Next(OverlayPane p) { pane = p; } 
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Main.mainPanel.remove(pane);
		Main.mainPanel.remove(pane.overlay);
		for (Component c : Main.mainPanel.getComponents()) {
			c.repaint();
		}
		for (OvalButton b : Main.globNav.buttons) {
			b.setEnabled(true);
		}
		Main.memEditor.bioFieldScroller.setEnabled(true);
		Main.memEditor.nameField.setEnabled(true);
			
		pane.done();
		pane.overlay.done();
		
	}

}
