package application.swing.projectinterface.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import application.swing.main.Main;

public class ConnectionChecker implements ActionListener {

	static boolean connected = true;
	static boolean reconnected = true;
	static JPanel old;
	static JPanel connect;
	static String errMsgHTML = "<html><body>"
			+ "Connection to the server could not be established. <br></br>"
			+ "Please ensure you are connected to the internet. <br></br>"
			+ "If this problem persists please contact the administrator: <br></br>"
			+ "<br></br>"
			+ "Email: sthomps9@oswego.edu <br></br>"
			+ "</body></html>";
	
	public ConnectionChecker() {
		connect = getConnectionScreen();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean startStatus = connected;
		
		connected = canConnect();
		
		if (startStatus != connected) {
			if (!connected) {
				old = Main.mainPanel.activePanel;
				Main.mainPanel.removeAll();
				Main.mainPanel.setActivePanel(connect);
			} else {
				if (old != null) {
					Main.mainPanel.removeAll();
					Main.mainPanel.setActivePanel(old);
				}
			}
		}
	}
	
	public static boolean canConnect() {
		try {
			URL url = new URL("http://67.246.103.207:3000");
	        url.openConnection().connect();
	        return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	JPanel getConnectionScreen() {
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(255,175,175));
		JLabel label = new JLabel(errMsgHTML);
		JLabel labelShadow = new JLabel(errMsgHTML);
		label.setBounds(50, Main.window.getHeight()/2 - 100, 1000, 200);
		labelShadow.setBounds(52, Main.window.getHeight()/2 - 98, 1000, 200);
		label.setForeground(Color.white);
		labelShadow.setForeground(new Color(255,120,120));
		label.setFont(new Font("Lexend", Font.BOLD, 30));
		labelShadow.setFont(new Font("Lexend", Font.BOLD, 30));
		label.setBorder(new EmptyBorder(-10,40,10,10));
		labelShadow.setBorder(new EmptyBorder(-10,40,10,10));
		p.add(label);
		p.add(labelShadow);
		reconnected = false;
		return p;
	}

}
