package application.swing.projectinterface.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import application.data.Family;
import application.data.Member;
import application.swing.main.Main;
import application.swing.projectinterface.actions.MemberButton;

public class TreePanel extends JPanel {

	private static final long serialVersionUID = -6500346212599399059L;

	public static int baseX = 200;
	public static int baseY = 200;
	public static Font font = new Font("Lexend", Font.PLAIN, 18);
	public static int baseWidth = 150;
	public static int baseHeight = 50;
	ArrayList<Line> lines = new ArrayList<>();
		
	public TreePanel() {
		Main.treePanel = this;
		setLayout(null);
		setMinimumSize(new Dimension(1000,1000));
		setBackground(Color.black);
	}
	
	public void draw() {
		Family fam = Main.activeFamily;
		
		drawMember(baseX, baseY, fam.getRootMember());
		
	}

	private void drawMember(int x, int y, Member toDraw) {

		JButton b = new JButton(toDraw.getName());
		toDraw.setButton(b);
		b.setFont(font);
		b.addActionListener(new MemberButton(toDraw));
		b.setBounds(x,y,baseWidth,baseHeight);
		add(b);
		toDraw.hasBeenDrawn(true);
		
		if (toDraw.hasChildren() || toDraw.hasSpouse()) {
			addDownLine(b.getBounds());
		}
		if (toDraw.hasParent()) {
			addUpLine(b.getBounds());
		}
		if (toDraw.hasSpouse() && !toDraw.getSpouse().getDrawnFlag()) {
			drawMember(x+500, y, toDraw.getSpouse());
			addSpouseLine(b.getBounds(), toDraw.getSpouse().getButton().getBounds());
		}
		if (toDraw.hasChildren()) {
			int cremainder = toDraw.getChildCount()%2;
			drawChildLine(toDraw, toDraw.getSpouse());
			if (cremainder == 1) {
				drawOddChildren(toDraw);
			} else {
				drawEvenChildren(toDraw);
			}
		}
		
	}

	private void drawChildLine(Member toDraw, Member spouse) {
		int x = getCenter(toDraw, spouse);
		int y = (int) (toDraw.getButton().getBounds().getY() + toDraw.getButton().getHeight() + 30);
		lines.add(new Line(x,y,x,y+300));
	}

	private void drawEvenChildren(Member parent) {
		// TODO Auto-generated method stub
		
	}

	private void drawOddChildren(Member parent) {
		int ccount = parent.getChildCount();
		while (ccount > 0) {
			drawMidChild(parent.getChild(ccount-1));
			ccount -= 1;
			if (ccount > 0) {
				drawRightChild(parent.getChild(ccount-1));
				ccount -= 1;
				drawLeftChild(parent.getChild(ccount-1));
			}
		}
		
	}
	private void drawLeftChild(Member toDraw) {
		
		
	}
	private void drawRightChild(Member toDraw) {
		
		
	}
	private void drawMidChild(Member toDraw) {
		
		
	}
	

	private void addDownLine(Rectangle bounds) {
		int x = (int) bounds.getCenterX();
		int y = (int) (bounds.getHeight() + bounds.getY());
		lines.add(new Line(x,y,x,y+30));
	}
	private void addUpLine(Rectangle bounds) {
		int x = (int) bounds.getCenterX();
		int y = (int) bounds.getY();
		lines.add(new Line(x,y,x,y-30));
	}
	private void addSpouseLine(Rectangle bounds1, Rectangle bounds2) {
		int x1 = (int) bounds1.getCenterX();
		int x2 = (int) bounds2.getCenterX();
		int y = (int) (bounds1.getHeight() + bounds1.getY()) + 30;
		lines.add(new Line(x1,y,x2,y));
	}
	private int getCenter(Member m1, Member m2) {
		int difference = (int) (m2.getButton().getBounds().getCenterX() - m1.getButton().getBounds().getCenterX());
		return (int) (m1.getButton().getBounds().getCenterX() - difference);
	}
	
	@Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Line line : lines) {
        	g2.setStroke(new BasicStroke(4));
            g2.setColor(line.color);
            g2.drawLine(line.x1, line.y1, line.x2, line.y2);
        }
    }
	
}
