package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import application.data.Family;
import application.swing.main.Main;
import application.swing.projectinterface.components.TEI;
import application.swing.projectinterface.util.Phase;

public class NewTree implements ActionListener {

	JTextField name;
	
	public NewTree(JTextField field) { name = field; }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (name.getText() == "") return;
		Main.activeFamily = new Family(name.getText());
		Main.currentPhase = Phase.EDIT;
		Main.mainPanel.remove(Main.treeSelectionPanel);
		Main.tei = new TEI();
		Main.mainPanel.setActivePanel(Main.tei);
	}

}
