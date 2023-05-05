package application.swing.projectinterface.treecomponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import application.data.Member;
import application.swing.main.Main;
import application.swing.projectinterface.util.Line;

public class TreePanel extends JPanel {

	private static final long serialVersionUID = -6500346212599399059L;

	public static int baseX = 300;
	public static int baseY = 300;
	public static Font font = new Font("Lexend", Font.PLAIN, 18);
	public static int baseWidth = 150;
	public static int baseHeight = 50;
	public static int spacer = 200;
	ArrayList<Line> lines = new ArrayList<>();
	ArrayList<MemberButton> buttons = new ArrayList<>();
	ArrayList<Member> ignoreChildLine = new ArrayList<>();
	boolean firstDrawDone;
			
	public TreePanel() {
		Main.treePanel = this;
		setPreferredSize(new Dimension(4000,4000));
		setLayout(null);
		setBackground(Color.black);
		
	}
	
//	public static void main(String[] args) {
//		
//		createFamily();
//		
//		TreePanel tp = new TreePanel();
//		JFrame window = new JFrame();
//		
//		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		window.setResizable(true);
//		window.setTitle("Family Tree");
//		window.setPreferredSize(new Dimension(1280, 720));
//		window.setSize(window.getPreferredSize());
//		window.setMinimumSize(new Dimension(800, 600));
//		window.add(tp);
//		window.setVisible(true);
//		window.pack();
//		
//		for (Member m : fam.getMembers()) {
//			m.setButton(new MemberButton(m));
//			tp.add(m.getButton());
//		}
//		
//		
//		
//	}
	

	
	public void draw() {
		
		ignoreChildLine.clear();
		removeAll();
		resetFlags();
//		if (Main.activeMember == null) {
//			recurseMembers(Main.activeFamily.getRootMember(), null);
//		} else {
//			recurseMembers(Main.activeMember, null);
//		}
		recurseMembers(Main.activeFamily.getRootMember(), null);
		removeUndrawns();
		checkY();
		checkX();
		checkCollisions();
		addButtons();
		
	}
	
	void removeUndrawns() {
		ArrayList<Member> toRemove = new ArrayList<>();
		for (Member m : Main.activeFamily.getMembers()) {
			if (!m.getDrawnFlag()) {
				toRemove.add(m);
			}
		}
		for (Member m : toRemove) {
			Main.activeFamily.removeMember(m);
		}
	}
	
	private void checkCollisions() {
		ArrayList<JButton> butts = new ArrayList<>();
		for (Member m : Main.activeFamily.getMembers()) {
			butts.add(m.getButton());
		}
		for (JButton b : butts) {
			ArrayList<JButton> otherbutts = new ArrayList<>();
			for (JButton b2 : butts) {
				if (b != b2) otherbutts.add(b2);
			}
			for (JButton otherbutt : otherbutts) {
				if (b.getBounds().intersects(otherbutt.getBounds())) {
					//COLLISION DETECTED
					int b1x = (int) b.getBounds().getX();
					int b2x = (int) otherbutt.getBounds().getX();
					if (b1x > b2x) {
						b.setBounds(createBounds(b1x + spacer, b.getBounds().getY()));
					} else {
						otherbutt.setBounds(createBounds(b2x + spacer, otherbutt.getBounds().getY()));
					}
				}
			}
		}
		
	}

	private void checkX() {
		int lowest = 40;
		for (Member m : Main.activeFamily.getMembers()) {
			int x = (int) m.getButton().getBounds().getX();
			if (x < lowest) lowest = x;
		}
		if (lowest < 40) {
			int deltaX = 40 - lowest;
			for (Member m : Main.activeFamily.getMembers()) {
				Rectangle bounds = m.getButton().getBounds();
				bounds.setBounds((int)bounds.getX() + deltaX, (int)bounds.getY(), baseWidth, baseHeight);
				m.setBounds(bounds);
			}
		}
	}
	
	private void checkY() {
		int lowest = 40;
		for (Member m : Main.activeFamily.getMembers()) {
			int y = (int) m.getButton().getBounds().getY();
			if (y < lowest) lowest = y;
		}
		if (lowest < 40) {
			int deltaY = 40 - lowest;
			for (Member m : Main.activeFamily.getMembers()) {
				Rectangle bounds = m.getButton().getBounds();
				bounds.setBounds((int)bounds.getX(), (int)bounds.getY() + deltaY, baseWidth, baseHeight);
				m.setBounds(bounds);
			}
		}
	}

	private void resetFlags() {
		for (Member m : Main.activeFamily.getMembers()) {
			m.hasBeenDrawn(false);
		}
	}

	void addButtons() {
		for (Member m : Main.activeFamily.getMembers()) {
			if (m.equals(Main.activeMember)) {
				JButton b = m.getButton();
				b.setBorder(BorderFactory.createLineBorder(new Color(255,120,120), 3));
				m.setButton(b);
			} else {
				JButton b = m.getButton();
				b.setBorder(null);
				m.setButton(b);
			}
			add(m.getButton());
		}
	}
	
	void recurseMembers(Member currentMember, Member lastMember) {
		boolean root;
		if (lastMember == null) root = true;
		else root = false;
		
		Rectangle currentBounds;
		
		if (root) {
			currentBounds = createBounds(baseX,baseY);
			currentMember.setBounds(currentBounds);
		} else {
			currentBounds = currentMember.getButton().getBounds();
			currentMember.setBounds(currentBounds);
		}
		
		currentMember.hasBeenDrawn(true);
		
		//DRAW PARENT ONE (LEFT)
		if (currentMember.getParentOne() != null && !currentMember.getParentOne().getDrawnFlag()) {
			Member p1 = currentMember.getParentOne();
			if (currentMember.getParentTwo() == null) {
				//SINGLE PARENT (LEFT)
				p1.setBounds(createBounds(currentBounds.getX(), currentBounds.getY() - spacer));
			} else {
				//BOTH PARENTS (LEFT)
				p1.setBounds(createBounds(currentBounds.getX()-spacer, currentBounds.getY() - spacer));
			}
			recurseMembers(p1, currentMember);
		}
		//DRAW PARENT TWO (RIGHT)
		if (currentMember.getParentTwo() != null && !currentMember.getParentTwo().getDrawnFlag()) {
			Member p2 = currentMember.getParentTwo();
			if (currentMember.getParentOne() == null) {
				//SINGLE PARENT (RIGHT)
				p2.setBounds(createBounds(currentBounds.getX(), currentBounds.getY() - spacer));
			} else {
				//BOTH PARENTS (RIGHT)
				p2.setBounds(createBounds(currentBounds.getX()+spacer, currentBounds.getY() - spacer));
			}
			recurseMembers(p2, currentMember);
		}
		//DRAW CHILDREN
		if (currentMember.hasChildren()) {
			ArrayList<Member> children = new ArrayList<>();
			for (Member c : currentMember.getChildren()) {
				if (!children.contains(c)) children.add(c);
			}
			
			for (int i = 0; i < children.size(); i++) {
				if (!currentMember.hasSpouse()) {
					//DRAW DIRECTLY BELOW
					Member child = children.get(i);
					if (!child.getDrawnFlag()) {
						child.setBounds(createBounds(currentBounds.getX() + (i*(baseWidth + spacer)), currentBounds.getY() + spacer));
						recurseMembers(child, currentMember);
					}
				} else {
					//DRAW CENTERED w/ SPOUSE
					Member child = children.get(i);
					if (!child.getDrawnFlag()) {
						child.setBounds(createBounds(currentBounds.getX() + spacer + (i*(baseWidth + spacer)), currentBounds.getY() + spacer));
						recurseMembers(child, currentMember);
					}
				}
			}
		}
		//DRAW SPOUSE
		if (currentMember.hasSpouse() && !currentMember.getSpouse().getDrawnFlag()) {
//			if (!root && !lastMember.equals(currentMember.getSpouse())) {
				Member sps = currentMember.getSpouse();
				sps.setBounds(createBounds(currentBounds.getX() + (2*spacer), currentBounds.getY()));
				recurseMembers(sps, currentMember);
//			}
			
		}
		//CHECK SPOUSE OVERLAY BUG
		if (Main.activeMember != null) {
			if (currentMember.getSpouse() != null) {
				Member sps = currentMember.getSpouse();
				if (currentMember.getButton().getBounds().getX() == 
					sps.getButton().getBounds().getX()) {
					if (Main.activeMember.equals(currentMember)) {
						sps.setBounds(createBounds(currentBounds.getX() + (2*spacer), currentBounds.getY()));
					}
					
				}
			}
		}
		
		
		
		
		
	}
	
	Rectangle createBounds(double x, double y) {
		Rectangle r = new Rectangle();
		r.setBounds((int)x,(int)y,baseWidth,baseHeight);
		return r;
	}
	
	@Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.white);
        
        for (Member m : Main.activeFamily.getMembers()) {
            //DRAW DOWN LINES {
        	if (m.hasChildren() || m.hasSpouse()) {
        		Rectangle bounds = m.getButton().getBounds();
        		int x = (int) bounds.getCenterX();
        		int y = (int) bounds.getY() + baseHeight;
        		g2.drawLine(x, y, x, y+20);
        	}
        	//DRAW UP LINES
        	if (m.hasParent()) {
        		Rectangle bounds = m.getButton().getBounds();
        		int x = (int) bounds.getCenterX();
        		int y = (int) bounds.getY();
        		g2.drawLine(x, y, x, y-20);
        	}
        	//DRAW CONNECTING CHILD LINES
        	if (m.hasChildren()) {
        		int x1 = -1;
        		int x2 = -1;
        		int y = 0;
        		for (Member child : m.getChildren()) {
        			int cx = (int) child.getButton().getBounds().getCenterX();
        			if (x1 == -1) x1 = cx;

        			if (x1 > cx) x1 = cx;
        			if (x2 < cx) x2 = cx;	
        			y = (int) (child.getButton().getBounds().getY()-20);
        		}
        		g2.drawLine(x1, y, x2, y);
        	}
        	//DRAW CONNECTING SPOUSE LINES
        	if (m.hasSpouse()) {
        		Rectangle bounds = m.getButton().getBounds();
        		Rectangle spousebounds = m.getSpouse().getButton().getBounds();
        		int x1 = (int) bounds.getCenterX();
        		int x2 = (int) spousebounds.getCenterX();
        		int y = (int) bounds.getMaxY() + 20;
        		g2.drawLine(x1, y, x2, y);
        	}
        	//DRAW PARENT TO CHILDREN LINES
        	if (!ignoreChildLine.contains(m)) {
        		if (m.hasChildren() && !m.hasSpouse()) {
            		//STRAIGHT DOWN
            		Rectangle bounds = m.getButton().getBounds();
            		int x = (int)bounds.getCenterX();
            		int y1 = (int)(bounds.getY() + bounds.getHeight() + 20);
            		int y2 = y1 + (spacer-40-baseHeight);
            		g2.drawLine(x, y1, x, y2);
            	}
            	if (m.hasChildren() && m.hasSpouse()) {
            		//CENTERED & DOWN
            		Rectangle bounds = m.getButton().getBounds();
            		Rectangle spousebounds = m.getSpouse().getButton().getBounds();
            		double dist = (spousebounds.getCenterX() - bounds.getCenterX())/2.0;
            		int x = (int)(bounds.getCenterX() + dist);
            		int y1 = (int)(bounds.getY() + bounds.getHeight() + 20);
            		int y2 = y1 + (spacer-40-baseHeight);
            		g2.drawLine(x, y1, x, y2);
            	}
            	ignoreChildLine.add(m.getSpouse());
        	}
        	
        }
    }
	
//	static void createFamily() {
//		fam = new Family("Default");
//		Member m1 = new Member("John", fam);
//		Member m2 = new Member("Johnna", "", m1, fam);
//		Member m3 = new Member("Johnny", "", m1, m2, fam);
//		fam.setRootMember(m1);
//		for (Member m : fam.getMembers()) m.setButton(new MemberButton(m));
//	}
}
