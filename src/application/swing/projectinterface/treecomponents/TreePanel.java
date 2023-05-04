package application.swing.projectinterface.treecomponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import application.data.Family;
import application.data.Member;
import application.swing.projectinterface.util.Line;

public class TreePanel extends JPanel {

	private static final long serialVersionUID = -6500346212599399059L;

	public static int baseX = 200;
	public static int baseY = 200;
	public static Font font = new Font("Lexend", Font.PLAIN, 18);
	public static int baseWidth = 150;
	public static int baseHeight = 50;
	ArrayList<Line> lines = new ArrayList<>();
	
	static Family fam;
		
	public TreePanel() {
//		Main.treePanel = this;
		setLayout(null);
		setMinimumSize(new Dimension(1000,1000));
		setBackground(Color.black);
	}
	
	public static void main(String[] args) {
		
		createFamily();
		
		TreePanel tp = new TreePanel();
		JFrame window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("Family Tree");
		window.setPreferredSize(new Dimension(1280, 720));
		window.setSize(window.getPreferredSize());
		window.setMinimumSize(new Dimension(800, 600));
		window.add(tp);
		window.setVisible(true);
		window.pack();
		
		for (Member m : fam.getMembers()) {
			m.setButton(new MemberButton(m));
			tp.add(m.getButton());
		}
		
		Timer t = new Timer(17, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tp.draw();
				
			}
			
		});
		
		t.start();
		
	}
	
	public void draw() {
		
		recurseMembers(fam.getRootMember());
		
	}
	
	void recurseMembers(Member root) {

		root.hasBeenDrawn(true);
		
		if (root.getSpouse() != null) {
			Rectangle bounds = root.getSpouse().getButton().getBounds();
			System.out.println(bounds);
			bounds.add(100, 0);
			System.out.println(bounds);
			root.getSpouse().getButton().setBounds(bounds);
		}
		
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
	
	static void createFamily() {
		fam = new Family("Default");
		Member m1 = new Member("John", fam);
		Member m2 = new Member("Johnna", "", m1, fam);
		Member m3 = new Member("Johnny", "", m1, m2, fam);
		fam.setRootMember(m1);
		for (Member m : fam.getMembers()) m.setButton(new MemberButton(m));
	}
}
