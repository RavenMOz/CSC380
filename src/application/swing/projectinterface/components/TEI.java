package application.swing.projectinterface.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import application.swing.main.Main;
import application.swing.projectinterface.treecomponents.TreePanel;
import application.swing.projectinterface.util.FamilyTree;
import application.swing.projectinterface.util.FamilyUtil;

public class TEI extends JPanel {

	private static final long serialVersionUID = 1L;
	
	Thread teiThread;
	
	Graphics2D g2;
	BufferedImage screenIMG;
			
	public TEI() {
		
		super();
		
		setLayout(null);
				
		Main.tei = this;
		Main.memEditor = new MemberEditor();
		FamilyTree.setupFamilyPanel();
		Main.treeContainer = new TreeContainer();
		Main.globNav = new GlobalNavigation(this);
		
		sendToEditor();
				
	}

	void sendToEditor() {
		
		removeAll();
		
		add(Main.treeContainer, BorderLayout.CENTER);
		add(Main.memEditor, BorderLayout.EAST);
		add(Main.globNav, BorderLayout.NORTH);
		
		FamilyTree.draw();

	}
	
	public int getXforCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = Main.window.getWidth()/2 - length/2;
		return x;
	}

	public int getXforRightAlignedText(String text, int tailX) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
		
	}
	
	public void resize() {
		setBounds(0,0,Main.window.getWidth(), Main.window.getHeight());
		for (Component c : getComponents()) c.repaint();
		repaint();
	}	
	
}
