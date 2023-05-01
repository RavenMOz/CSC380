package application.swing.logininterface.components;

import java.awt.Color;

import javax.swing.JPanel;

import application.swing.logininterface.subcomponents.TreeSelectionPane;
import application.swing.main.Main;

public class TreeSelectionPanel extends JPanel {
	
	private static final long serialVersionUID = 1704876557859225374L;

	TreeSelectionPane subPanel;
	
	public TreeSelectionPanel() {
		super();
		
		Main.treeSelectionPane = new TreeSelectionPane();
		setLayout(null);
		
		subPanel = Main.treeSelectionPane;
		setBackground(Color.pink);
		
		add(subPanel);
		Main.treeSelectionPanel = this;
				
	}
	
	public void resize() {
		setBounds(0,0,Main.window.getWidth(), Main.window.getHeight());
		int width = (int)(Main.window.getWidth() * (3.0/5.0));
		if (width > 500) width = 500;
		subPanel.setBounds(Main.window.getWidth()/2 - width/2,
						   Main.window.getHeight()/8,
						   width,
						   (int)(Main.window.getHeight() * (5.5/8.0)));
	}
}
