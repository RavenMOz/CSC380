package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.data.Member;
import application.swing.main.Main;
import application.swing.projectinterface.util.FamilyTree;

public class AddParent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		Member parent = new Member("New Member", Main.activeFamily);
		parent.addChild(Main.activeMember);
		
		Main.activeMember.addParent(parent);
		
		Main.memEditor.nameField.setText(parent.getName());
		Main.memEditor.bioField.setText(parent.getBio());
		Main.globNav.save.setEnabled(true);

		try {
			Date d = new SimpleDateFormat("MM/dd/yyyy").parse(parent.getBMonth() + "/" + parent.getBDay() + "/" + parent.getBYear());
			Main.memEditor.bDayField.setValue(d);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Main.activeMember = parent;
		FamilyTree.draw();
		
	}

}
