package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.swing.main.Main;

public class NewTreeMenu implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.treeSelectionPane.addNewTreeComponents();
	}

}
