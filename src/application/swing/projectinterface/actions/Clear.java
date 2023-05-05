package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.data.Member;
import application.swing.main.Main;

public class Clear implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.activeFamily.clearMembers();
		Main.activeFamily.addMember(new Member("New Member", Main.activeFamily));
		Main.activeFamily.setRootMember(Main.activeFamily.getMember(0));
		
		Main.tei.editsMade = true;
		
	}

}
