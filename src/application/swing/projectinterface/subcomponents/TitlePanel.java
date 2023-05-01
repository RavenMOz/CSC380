package application.swing.projectinterface.subcomponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.swing.main.Main;

public class TitlePanel extends JPanel implements Runnable {
	
	Thread tpThread;

	private static final long serialVersionUID = -994441014195754445L;

	Graphics2D g2;
	BufferedImage img;
	
	JLabel nameLabel;
	JLabel nameLabelShadow;
	
	public TitlePanel(String famName) {
		super();

		setupGraphics();
		
		nameLabel = getLabel(famName, 5, true);
		nameLabelShadow = getLabelShadow(famName, 5, true);
		
		start();
		
	}
	
	void start() {
		tpThread = new Thread(this);
		tpThread.start();
	}
	
	private void setupGraphics() {
		
		Dimension newSize = new Dimension(Main.window.getWidth() - Main.memEditor.getWidth(), 40);
		setPreferredSize(newSize);
		setSize(newSize);
		
		setBackground(new Color(255,175,175));
		setBorder(BorderFactory.createLineBorder(Color.white));
		setLayout(null);
		
		img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) img.getGraphics();
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
	}
	
	private JLabel getLabel(String s, int y, boolean centered) {
		
		int x;
		
		if (centered) {
			x = getXforCenteredText(s);
		} else {
			x = 10;
		}
		
		JLabel l2 = new JLabel(s);
		l2.setFont(new Font("Lexend", Font.BOLD, 20));
		l2.setForeground(Color.white);
		l2.setBounds(x,y, 200, 30);
		add(l2);
		
		return l2;

	}
	
	private JLabel getLabelShadow(String s, int y, boolean centered) {
		
		int x;
		
		if (centered) {
			x = getXforCenteredText(s)+2;
		} else {
			x = 12;
		}
		
		JLabel l2 = new JLabel(s);
		l2.setFont(new Font("Lexend", Font.BOLD, 20));
		l2.setForeground(new Color(255,120,120));
		l2.setBounds(x,y+2, 200, 30);
		add(l2);

		return l2;
		
	}
	
	public int getXforCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = getWidth()/2 - length/2;
		return x;
	}

	public void resize() {
		
		Dimension newSize = new Dimension(Main.window.getWidth() - Main.memEditor.getWidth(), 40);
		setPreferredSize(newSize);
		setSize(newSize);
		
		nameLabel.setBounds(getXforCenteredText(nameLabel.getText())-15,nameLabel.getY(),nameLabel.getWidth(),nameLabel.getHeight());
		nameLabelShadow.setBounds(nameLabel.getX()+2,nameLabel.getY()+2,nameLabel.getWidth(),nameLabel.getHeight());
		
	}

	@Override
	public void run() {

		while (Main.thread != null) {
			resize();
		}
		
	}
	
}
