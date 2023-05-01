package application.swing.projectinterface.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.data.Member;
import application.swing.main.Main;
import application.swing.projectinterface.util.FamilyTree;

public class AddChild implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		doIt();
	}

	void doIt() {
		Main.treePanel.setMinimumSize(new Dimension(Main.treePanel.getWidth(), Main.treePanel.getHeight()+500));
		Member active = Main.activeMember;
		Member c = new Member("New Member", "", active, active.getSpouse(), Main.activeFamily);
		FamilyTree.draw();
		
		Main.memEditor.nameField.setText(c.getName());
		Main.memEditor.bioField.setText(c.getBio());
		Main.globNav.save.setEnabled(true);

		try {
			Date d = new SimpleDateFormat("MM/dd/yyyy").parse(c.getBMonth() + "/" + c.getBDay() + "/" + c.getBYear());
			Main.memEditor.bDayField.setValue(d);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Main.activeMember = c;
	}
	
}
