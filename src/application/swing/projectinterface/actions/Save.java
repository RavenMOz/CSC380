package application.swing.projectinterface.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import application.storage.SQLCommands;
import application.swing.main.Main;

public class Save implements ActionListener {
	
	public static long saveTimer = 0;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		for (Component c : Main.tei.getComponents()) {
			c.repaint();
		}
		
		Main.globNav.save.setEnabled(false);
		Main.globNav.save.setText("Saving...");
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SQLCommands.writeFamily(Main.activeFamily);
				Main.globNav.save.setText("Saved.");
				Main.globNav.save.setIcon(new ImageIcon("/check.png"));
				saveTimer = System.currentTimeMillis();
				Main.tei.editsMade = false;
			}
		});
		
		

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
