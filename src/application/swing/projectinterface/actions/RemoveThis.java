package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.swing.main.Main;

public class RemoveThis implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.activeFamily.removeMember(Main.activeMember);
//		Member toRemove = Main.activeMember;
		
		Main.tei.editsMade = true;

	}

}
