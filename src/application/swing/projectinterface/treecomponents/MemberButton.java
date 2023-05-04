package application.swing.projectinterface.treecomponents;

import java.awt.Rectangle;

import javax.swing.JButton;

import application.data.Member;

public class MemberButton extends JButton {

	public static int defWidth = 150;
	public static int defHeight = 50;
	public static int rootX = 300;
	public static int rootY = 300;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4035368642127113170L;
	
	public MemberButton(Member m) {
		
		super();
		
		setBounds(rootX, rootY, defWidth, defHeight);
		setText(m.getName());
		System.out.println(m.getName());
		
	}
	
	public static Rectangle getBounds(Member m) {
		return m.getButton().getBounds();
	}

}
