package application.swing.main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.Timer;

import com.formdev.flatlaf.FlatLightLaf;

import application.data.Family;
import application.data.Member;
import application.swing.logininterface.components.LoginPanel;
import application.swing.logininterface.components.TreeSelectionPanel;
import application.swing.logininterface.subcomponents.LoginPane;
import application.swing.logininterface.subcomponents.TreeSelectionPane;
import application.swing.logininterface.util.UserDB;
import application.swing.projectinterface.components.GlobalNavigation;
import application.swing.projectinterface.components.MemberEditor;
import application.swing.projectinterface.components.TEI;
import application.swing.projectinterface.components.TreeContainer;
import application.swing.projectinterface.subcomponents.TitlePanel;
import application.swing.projectinterface.treecomponents.TreePanel;
import application.swing.projectinterface.util.ButtonChecker;
import application.swing.projectinterface.util.ConnectionChecker;
import application.swing.projectinterface.util.FieldChecker;
import application.swing.projectinterface.util.Phase;
import application.swing.projectinterface.util.Resizer;

public class Main {

	public static JFrame window;
	public static Thread thread = new Thread();
	public static Family activeFamily;
	public static MainPanel mainPanel;
	public static TEI tei;
	public static GlobalNavigation globNav;
	public static MemberEditor memEditor;
	public static TreeContainer treeContainer;
	public static TreePanel treePanel;
//	public static TreePanel treePanel;
	public static Member activeMember;
	public static LoginPanel loginPanel;
	public static LoginPane loginPane;
	public static TreeSelectionPane treeSelectionPane;
	public static UserDB userDB;
	public static Phase currentPhase;
	public static JList<Family> familyList;
	public static TreeSelectionPanel treeSelectionPanel;
	public static TitlePanel titlePanel;

	public static void main(String[] args) {
		
		windowSetup();
		
	}

	private static void windowSetup() {
		
		FlatLightLaf.setup();
		
		userDB = new UserDB();
		
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("Family Tree");
		window.setPreferredSize(new Dimension(1280, 720));
		window.setSize(window.getPreferredSize());
		window.setMinimumSize(new Dimension(800, 600));
				
		currentPhase = Phase.LOGIN;
		mainPanel = new MainPanel(new LoginPanel());
		
		window.setContentPane(mainPanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		Timer t1 = new Timer(17, new Resizer());
		t1.start();
		Timer t2 = new Timer(17, new FieldChecker());
		t2.start();
		Timer t3 = new Timer(1000, new ConnectionChecker());
		t3.start();
		Timer t4 = new Timer(17, new ButtonChecker());
		t4.start();
		
		Timer t5 = new Timer(17, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (currentPhase == Phase.EDIT) {
					treePanel.draw();
				}
				
			}
			
		});
		
		t5.start();
		
		window.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        thread = null;
		        t1.stop();
		        t2.stop();
		        t3.stop();
		        t4.stop();
		        t5.stop();
		    }
		});
		
		
				
	}
	
}
