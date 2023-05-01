package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import application.data.Family;
import application.swing.main.Main;
import application.swing.projectinterface.components.TEI;
import application.swing.projectinterface.util.Phase;

public class SelectTree implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (Main.familyList.getSelectedValue() != null) {
			Main.activeFamily = Main.familyList.getSelectedValue();
			Main.currentPhase = Phase.EDIT;
			Main.mainPanel.remove(Main.treeSelectionPanel);
			Main.tei = new TEI();
			Main.mainPanel.setActivePanel(Main.tei);
			
		}
	}

}
