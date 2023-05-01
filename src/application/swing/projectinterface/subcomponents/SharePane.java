package application.swing.projectinterface.subcomponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.swing.main.Main;
import application.swing.main.MainPanel;
import application.swing.projectinterface.actions.ConfirmShare;

public class SharePane extends JPanel {

	private static final long serialVersionUID = -8092436119742191520L;

	public Overlay overlay;
	JTextField unameField;
	
	
	public SharePane() {
		
		super();
		
		overlay = new Overlay();
		
		resize();
		
		Main.mainPanel.add(overlay, MainPanel.PALETTE_LAYER);
		
		setBackground(new Color(255,150,150));
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(255,150,150), 4), BorderFactory.createLineBorder(Color.white, 6)));
				
		add(getFormattedLabel("Enter a user:"));
		unameField = new JTextField(15);
		add(unameField);
		Button b = new Button("Share", new ConfirmShare(unameField, this));
		add(b);
		
		
	}
	
	void resize() {
		
		setSize(new Dimension(300, 400));
		setLocation((Main.window.getWidth()/2)-(getWidth()/2), Main.window.getHeight()/2 - getHeight()/2 - 20);
		
	}
	
	JLabel getFormattedLabel(String txt) {
		
		JLabel text = new JLabel(txt);
		text.setFont(new Font("Lexend", Font.BOLD, 24));
		text.setForeground(Color.white);
		
		return text;
	}
	
}
