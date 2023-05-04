package application.swing.main;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MainPanel extends JLayeredPane {
	
	Thread containerThread;
	public JPanel activePanel;
	
	private static final long serialVersionUID = -3405111365170185512L;
	
	public MainPanel(JPanel panel) {
		activePanel = panel;
		panel.setBounds(0, 0, Main.window.getWidth(), Main.window.getHeight());
		add(panel, JLayeredPane.DEFAULT_LAYER);
		setVisible(true);
	}
	
	public void setActivePanel(JPanel panel) {
		activePanel = panel;
		panel.setBounds(0, 0, Main.window.getWidth(), Main.window.getHeight());
		add(panel, JLayeredPane.DEFAULT_LAYER);
		setVisible(true);
	}

	public void resize() {
		setSize(Main.window.getSize());
	}
	
}
