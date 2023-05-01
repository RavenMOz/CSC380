package application.swing.projectinterface.subcomponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import application.swing.projectinterface.util.OvalButton;

public class Button extends OvalButton {

	private static final long serialVersionUID = 6237417534876335041L;

	public Button(String txt, ActionListener action) {
		super(OvalButton.SHAPE_CAPSULE, OvalButton.HORIZONTAL,
				new Color(255,100,100),new Color(255,70,70),Color.white,Color.white);
		setMargin(new Insets(1, 1, 1, 1));
		setText(txt);
		setFont(new Font("Lexend", Font.BOLD, 20));
		setDoubleBuffered(true);
		setBorderThickness(2);
		setRadius(.4);
		setForeground(Color.white);
		setPreferredSize(new Dimension(120,50));
		addActionListener(action);
	}
}


