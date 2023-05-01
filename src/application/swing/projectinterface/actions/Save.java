package application.swing.projectinterface.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import application.storage.SQLCommands;
import application.swing.main.Main;
import application.swing.projectinterface.subcomponents.Button;

public class Save implements ActionListener {
	
	public static long saveTimer = 0;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		SQLCommands.writeFamily(Main.activeFamily);
		
		for (Component c : Main.tei.getComponents()) {
			c.repaint();
		}
		
		Main.globNav.save.setText("Saved.");
		Main.globNav.save.setIcon(new ImageIcon("/check.png"));
		saveTimer = System.currentTimeMillis();

//		for (OvalButton b : Main.globNav.buttons) {
//			b.setEnabled(false);
//		}
//		
//		Main.memEditor.bioFieldScroller.setEnabled(false);
//		Main.memEditor.nameField.setEnabled(false);
//		
//		Main.mainPanel.add(new PopupPane("Tree Saved."), JLayeredPane.POPUP_LAYER);		
//		
	}

}
