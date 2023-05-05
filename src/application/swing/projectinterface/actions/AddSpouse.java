package application.swing.projectinterface.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.data.Member;
import application.swing.main.Main;

public class AddSpouse implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.treePanel.setMinimumSize(new Dimension(Main.treePanel.getWidth() + 500, Main.treePanel.getHeight()));
		Member active = Main.activeMember;
		Member spouse = new Member("New Member", "", Main.activeFamily);
		active.setSpouse(spouse);
		spouse.setSpouse(active);
		
		Main.tei.editsMade = true;

	}

}
