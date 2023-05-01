package application.swing.projectinterface.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import application.swing.main.Main;
import application.swing.projectinterface.actions.Save;

public class FieldChecker implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if (!Main.currentPhase.equals(Phase.EDIT)) return;
		toggleFields();
		updateFields();
		
	}

	private void toggleFields() {
		
		if (Main.activeMember == null &&
			Main.memEditor.nameField.isEnabled()) {
			Main.memEditor.nameField.setEnabled(false);
		}
		if (Main.activeMember != null &&
			!Main.memEditor.nameField.isEnabled()) {
			Main.memEditor.nameField.setEnabled(true);
		}
		if (Main.activeMember == null &&
			Main.memEditor.bioField.isEnabled()) {
			Main.memEditor.bioField.setEnabled(false);
		}
		if (Main.activeMember != null &&
			!Main.memEditor.bioField.isEnabled()) {
			Main.memEditor.bioField.setEnabled(true);
		}
		
		
	}

	private void updateFields() {
		
		if (Main.activeMember == null) return;
		
		if (!Main.activeMember.getName().equals(Main.memEditor.nameField.getText())) {
			Main.activeMember.setName(Main.memEditor.nameField.getText());
		}
		if (!Main.activeMember.getBio().equals(Main.memEditor.bioField.getText())) {
			Main.activeMember.setBio(Main.memEditor.bioField.getText());
		}
		if (!Main.activeMember.getButton().getText().equals(Main.memEditor.nameField.getText())) {
			Main.activeMember.getButton().setText(Main.memEditor.nameField.getText());
		}

		LocalDate bday = ((Date)Main.memEditor.bDayField.getValue()).toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		if (Main.activeMember.getBDay() != bday.getDayOfMonth()) {
			Main.activeMember.setBDay(bday.getDayOfMonth());
		}
		if (Main.activeMember.getBMonth() != bday.getMonth().getValue()) {
			Main.activeMember.setBMonth(bday.getMonth().getValue());
		}
		if (Main.activeMember.getBYear() != bday.getYear()) {
			Main.activeMember.setBYear(bday.getYear());
		}
		if ((System.currentTimeMillis() - Save.saveTimer) > 1000) {
			Main.globNav.save.setIcon(null);
			Main.globNav.save.setText("Save");
		}
	
	}

}
