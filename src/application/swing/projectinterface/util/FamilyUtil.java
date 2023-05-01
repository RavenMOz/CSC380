package application.swing.projectinterface.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;

import application.data.Family;
import application.data.Member;
import application.swing.main.Main;
import application.swing.projectinterface.actions.MemberButton;

public class FamilyUtil {
	
	public static int baseX = 200;
	public static int baseY = 200;
	public static Font font = new Font("Lexend", Font.PLAIN, 18);
	public static int baseWidth = 150;
	public static int baseHeight = 50;
	
	static JPanel panel;
	
	public static void setupFamilyPanel() {
		Main.treePanel = new JPanel();
		Main.treePanel.setLayout(null);
		Main.treePanel.setMinimumSize(new Dimension(1000,1000));
		Main.treePanel.setBackground(Color.black);
	}
	
	public static void draw() {
		Family fam = Main.activeFamily;
		panel = Main.treePanel;
		
		drawMember(baseX, baseY, fam.getRootMember());
		
	}

	private static void drawMember(int x, int y, Member toDraw) {

		JButton b = new JButton(toDraw.getName());
		toDraw.setButton(b);
		b.setFont(font);
		b.addActionListener(new MemberButton(toDraw));
		b.setBounds(x,y,baseWidth,baseHeight);
		panel.add(b);
		
		if (toDraw.hasChildren() || toDraw.hasSpouse()) {
			addDownLine(b.getBounds());
		}
		
	}

	private static void addDownLine(Rectangle bounds) {
		int x = (int) bounds.getCenterX();
		int y = (int) (bounds.getHeight() + bounds.getY());
		panel.add(new Line(x,y,x,y));
	}

}
