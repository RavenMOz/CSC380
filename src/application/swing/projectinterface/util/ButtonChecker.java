package application.swing.projectinterface.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.swing.main.Main;
import application.swing.projectinterface.subcomponents.SharePane;

public class ButtonChecker implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		if (Main.currentPhase == Phase.EDIT) {
			if (Main.activeMember != null) {
				Main.memEditor.addchild.setEnabled(true);
				Main.memEditor.addparent.setEnabled(true);
				Main.memEditor.addspouse.setEnabled(true);
				Main.memEditor.removethis.setEnabled(true);
				
				if (Main.activeMember.hasTwoParents()) {
					Main.memEditor.addparent.setEnabled(false);
				} else {
					Main.memEditor.addparent.setEnabled(true);
				}
				
				if (Main.activeMember.hasSpouse()) {
					Main.memEditor.addspouse.setEnabled(false);
				} else {
					Main.memEditor.addspouse.setEnabled(true);
				}
				
			} else {
				Main.memEditor.addchild.setEnabled(false);
				Main.memEditor.addparent.setEnabled(false);
				Main.memEditor.addspouse.setEnabled(false);
				Main.memEditor.removethis.setEnabled(false);
			}
			
			if (Main.tei.editsMade) {
				Main.globNav.save.setEnabled(true);
			} else {
				Main.globNav.save.setEnabled(false);
			}
			
		}
		if (Main.currentPhase == Phase.LOGIN) {
			if (Main.loginPane.phase == SubPhase.NEWUSER) {
				if (Main.loginPane.viableFields()) {
					Main.loginPane.createUser.setEnabled(true);
				} else {
					Main.loginPane.createUser.setEnabled(false);
				}
			}
			if (Main.loginPane.phase == SubPhase.LOGIN) {
				if (Main.loginPane.viableFields()) {
					Main.loginPane.login.setEnabled(true);
				} else {
					Main.loginPane.login.setEnabled(false);
				}
			}
		}
		if (Main.currentPhase == Phase.TREESELECT) {
			if (!Main.treeSelectionPane.newTreeMode) {
				if (Main.familyList.getSelectedIndex() == -1) {
					Main.treeSelectionPane.delete.setEnabled(false);
				} else {
					Main.treeSelectionPane.delete.setEnabled(true);
				}
			} else {
		if (Main.treeSelectionPane.treeNameField.getText().length() > 0) {
					Main.treeSelectionPane.create.setEnabled(true);
				} else {
					Main.treeSelectionPane.create.setEnabled(false);
				}
			}
		}
		if (SharePane.sharebutton != null) {
			String txt = SharePane.unameField.getText();
			if (txt.length() > 0) {
				SharePane.sharebutton.setEnabled(true);
			} else {
				SharePane.sharebutton.setEnabled(false);
			}
		}
		
	}

}
