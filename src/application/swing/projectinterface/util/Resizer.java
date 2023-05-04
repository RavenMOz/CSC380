package application.swing.projectinterface.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.swing.main.Main;

public class Resizer implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Main.mainPanel.resize();
		
		switch(Main.currentPhase) {
		case EDIT: {
			Main.tei.resize();
			Main.memEditor.resize();
			Main.globNav.resize();
			Main.treeContainer.resize();
			Main.titlePanel.resize();
		}
		break;
		case LOGIN: {
			Main.loginPanel.resize();
			Main.loginPane.resize();

		}
		break;
		case TREESELECT: {
			Main.treeSelectionPanel.resize();
			Main.treeSelectionPane.resize();
			Main.treeSelectionPane.checkButtons();
		}
		break;
		case POPUP: Main.tei.resize();
		break;
		}
	}

}
