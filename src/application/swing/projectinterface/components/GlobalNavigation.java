package application.swing.projectinterface.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.swing.main.Main;
import application.swing.projectinterface.actions.Clear;
import application.swing.projectinterface.actions.Close;
import application.swing.projectinterface.actions.Export;
import application.swing.projectinterface.actions.Save;
import application.swing.projectinterface.actions.Share;
import application.swing.projectinterface.subcomponents.Button;

public class GlobalNavigation extends JPanel {
	
	TEI tei;
	Thread globNavThread;
	JLabel spacer;
	
	public Button save;
	public Button export;
	public Button close;
	public Button clear;
	public Button share;
	
	public Button[] buttons;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GlobalNavigation(TEI t) {
		
		super();
		
		tei = t;
	
		setLayout(null);
		
		setBackground(new Color(255,150,150));
		setBorder(BorderFactory.createLineBorder(Color.white));
		
		save = new Button("Save", new Save());
		close = new Button("Close", new Close());
		clear = new Button("Clear", new Clear());
		export = new Button("Export", new Export());
		share = new Button("Share", new Share());
		
		buttons = new Button[5];
		buttons[0] = save;
		buttons[1] = close;
		buttons[2] = clear;
		buttons[3] = export;
		buttons[4] = share;
		
		save.setEnabled(false);
		
		add(save);
		add(close);
		add(clear);
		add(export);
		add(share);
				
	}

	public void resize() {
		
		repaint();

		setPreferredSize(new Dimension(Main.window.getWidth(), 70));
		setSize(getPreferredSize());
		setMinimumSize(new Dimension(Main.window.getWidth(), 70));
		
		int buttwidth = (int) save.getPreferredSize().getWidth();
		int buttheight = (int) save.getPreferredSize().getHeight();
		
		int y = 35 - (buttheight/2);
		int x = Main.window.getWidth();
		
		save.setBounds(5, y, buttwidth, buttheight);
		close.setBounds(10+buttwidth, y, buttwidth, buttheight);
		clear.setBounds(15+buttwidth*2, y, buttwidth, buttheight);
		export.setBounds(x-buttwidth-20, y, buttwidth, buttheight);
		share.setBounds(x-(buttwidth*2)-25, y, buttwidth, buttheight);
		
		for (Component c : getComponents()) c.repaint();
	}
		

}
