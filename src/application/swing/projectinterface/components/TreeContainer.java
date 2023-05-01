package application.swing.projectinterface.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import application.swing.main.Main;
import application.swing.projectinterface.subcomponents.TitlePanel;

public class TreeContainer extends JPanel {

	private static final long serialVersionUID = 2248576531853711801L;

	JScrollPane scroller;
	
	public TreeContainer() {
		super();
		
		setLayout(new BorderLayout());
		
		setBorder(BorderFactory.createLineBorder(Color.white));
		scroller = new JScrollPane((JPanel)Main.treePanel);
		scroller.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		scroller.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
		Main.titlePanel = new TitlePanel(Main.activeFamily.getName());
		add(Main.titlePanel, BorderLayout.NORTH);
		add(scroller, BorderLayout.CENTER);
				
	}
	
	public void resize() {
//		setPreferredSize(new Dimension((int) (Main.window.getWidth()*(2.0/3.0)), Main.window.getHeight() - Main.globNav.getHeight()));
//		scroller.setPreferredSize(new Dimension((int) (Main.window.getWidth()*(2.0/3.0)), Main.window.getHeight() - Main.globNav.getHeight()));
		
		int x = 0;
		int y = Main.globNav.getHeight();
		int width = (Main.window.getWidth()/3)*2;
		int height = Main.window.getHeight() - Main.globNav.getHeight();
		
		setBounds(x,y,width,height);
		scroller.setSize(getSize());
		scroller.setMaximumSize(new Dimension(getWidth(), Main.window.getHeight() - Main.globNav.getHeight() - Main.titlePanel.getHeight()));
//		scroller.setBounds(x,Main.titlePanel.getHeight(),width,height);
//		Main.treePanel.setBounds(0,0, width, height);
		
		for (Component c : getComponents()) c.repaint();
	}
	
}
