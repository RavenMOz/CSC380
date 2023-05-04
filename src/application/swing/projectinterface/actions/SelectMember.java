package application.swing.projectinterface.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.data.Member;
import application.swing.main.Main;

public class SelectMember implements ActionListener {

	Member member;
	
	public SelectMember(Member m) {
		member = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Main.memEditor.nameField.setText(member.getName());
		Main.memEditor.bioField.setText(member.getBio());
		Main.globNav.save.setEnabled(true);

		try {
			Date d = new SimpleDateFormat("MM/dd/yyyy").parse(member.getBMonth() + "/" + member.getBDay() + "/" + member.getBYear());
			Main.memEditor.bDayField.setValue(d);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Main.activeMember = member;
		Main.memEditor.checkButtons();
	}

}
