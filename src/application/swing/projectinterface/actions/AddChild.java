package application.swing.projectinterface.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.data.Member;
import application.swing.main.Main;

public class AddChild implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		doIt();
	}

	void doIt() {
		Main.treePanel.setMinimumSize(new Dimension(Main.treePanel.getWidth(), Main.treePanel.getHeight()+500));
		Member active = Main.activeMember;
		Member spouse = Main.activeMember.getSpouse();
		Member c = new Member("New Member", "", Main.activeFamily);
		
		active.addChild(c);
		c.addParent(active);
		
		if (spouse != null) {
			spouse.addChild(c);
			c.addParent(spouse);
		}
		
		Main.tei.editsMade = true;

	}
	
}
