package application.swing.logininterface.subcomponents;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import application.swing.logininterface.util.UserDB;
import application.swing.main.Main;
import application.swing.projectinterface.actions.BackToSelect;
import application.swing.projectinterface.actions.DeleteTree;
import application.swing.projectinterface.actions.Logout;
import application.swing.projectinterface.actions.NewTree;
import application.swing.projectinterface.actions.NewTreeMenu;
import application.swing.projectinterface.actions.SelectTree;
import application.swing.projectinterface.subcomponents.Button;

public class TreeSelectionPane extends JPanel{

	private static final long serialVersionUID = -5058124946547332880L;

	Thread tspThread;
	JScrollPane scrollableFamily;
	Button nextButton;
	Button signOut;
	Button newTree;
	public Button delete;
	
	JLabel label;
	JLabel user;
	
	JLabel instruct;
	public JTextField treeNameField;
	Button back;
	public Button create;
		
	public boolean newTreeMode = false;
	
	public TreeSelectionPane() {
		
//		super(new FlowLayout(FlowLayout.CENTER, 25, 50));
		super();
				
		setLayout(null);
		
		addSelectComponents();
				
	}

	public void addSelectComponents() {
				
		removeAll();
		newTreeMode = false;
				
		label = new JLabel("Welcome back, " + UserDB.activeUserName + "!");
		add(label);
		user = new JLabel("Your trees:");
		add(user);
	
		scrollableFamily = new JScrollPane();
		scrollableFamily.setViewportView(Main.familyList);
		scrollableFamily.setPreferredSize(new Dimension(300, 200));

		add(scrollableFamily);
		
		nextButton = new Button("Open", new SelectTree());
		add(nextButton);
		
		signOut = new Button("Sign Out", new Logout());
		add(signOut);
		
		newTree = new Button("New >", new NewTreeMenu());
		add(newTree);
		
		delete = new Button("Delete", new DeleteTree());
		add(delete);
		
		Font font = new Font("Lexend", Font.PLAIN, 16);
		
		label.setFont(new Font("Lexend", Font.BOLD, 20));
		user.setFont(font);
		Main.familyList.setFont(font);
		
	}
	
	public void addNewTreeComponents() {

		removeAll();
		newTreeMode = true;
		
		instruct = new JLabel("Enter a name for your tree:");
		add(instruct);
		
		treeNameField = new JTextField(15);
		add(treeNameField);
		
		create = new Button("Create", new NewTree(treeNameField));
		add(create);
		
		back = new Button("< Back", new BackToSelect());
		add(back);
		
		Font font = new Font("Lexend", Font.PLAIN, 16);
		
		instruct.setFont(new Font("Lexend", Font.BOLD, 20));
		treeNameField.setFont(font);
		
	}
	
	public void checkButtons() {
		try {
			Main.familyList.getSelectedValue().equals(null);
			if (!nextButton.isEnabled()) nextButton.setEnabled(true);
			if (!delete.isEnabled()) nextButton.setEnabled(true);
		} catch (Exception e) {
			if (nextButton.isEnabled()) nextButton.setEnabled(false);
			if (delete.isEnabled()) nextButton.setEnabled(false);
		}
	}
	
	public void resize() {
		
		if (newTreeMode) {
			int increment = getHeight()/8;
			
			center(increment, instruct);
			center(increment*2, treeNameField);
			
			Component[] cs = {back, create};
			groupCenter(getHeight() - 100, 10, cs);
		} else {
			int increment = getHeight()/8;
			
			center(increment, label);
			center(increment*2, user);
			center(increment*2 + 30, scrollableFamily);
			
			Component[] cs = {signOut, newTree};
			groupCenter(getHeight() - 140, 10, cs);
			Component[] cs2 = {delete, nextButton};
			groupCenter(getHeight() - 80, 10, cs2);
		}
		checkButtons();
		
		
	}
	
	void center(int y, Component c) {
		int x = (getWidth()/2) - (c.getWidth()/2);
		Rectangle bounds = new Rectangle(
				x,y,(int)c.getPreferredSize().getWidth(), (int)c.getPreferredSize().getHeight());
		c.setBounds(bounds);
	}
	
	void groupCenter(int y, int space, Component[] cs) {
		int x = (getWidth()/2) - (space * (cs.length-1));
		for (Component c : cs) {
			x -= (c.getWidth()/2);
		}
		for (Component c : cs) {
			c.setBounds(x,y,(int)c.getPreferredSize().getWidth(),(int)c.getPreferredSize().getHeight());
			x += c.getWidth(); x += space;
		}
		
	}
		
}
