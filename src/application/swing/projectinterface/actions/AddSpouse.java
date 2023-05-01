package application.swing.projectinterface.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.data.Member;
import application.swing.main.Main;
import application.swing.projectinterface.util.FamilyTree;

public class AddSpouse implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.treePanel.setMinimumSize(new Dimension(Main.treePanel.getWidth() + 500, Main.treePanel.getHeight()));
		Member active = Main.activeMember;
		new Member("New Member", "", active, Main.activeFamily);
		FamilyTree.draw();
	}

}
