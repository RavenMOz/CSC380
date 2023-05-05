package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.data.Member;
import application.swing.main.Main;

public class AddParent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		Member parent = new Member("New Member", Main.activeFamily);
		parent.addChild(Main.activeMember);
		
		Main.activeMember.addParent(parent);
		
		Main.tei.editsMade = true;

		
	}

}
