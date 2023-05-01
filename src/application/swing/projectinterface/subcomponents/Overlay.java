package application.swing.projectinterface.subcomponents;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import application.swing.main.Main;

public class Overlay extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6027329497141147163L;

	Thread overlaybkgThread;
	
	public Overlay() {
		
		super();
				
		resize();
		
		setBackground(new Color(200,100,100, 100));
				
		start();
		
	}

	private void start() {

		overlaybkgThread = new Thread(this);
		overlaybkgThread.start();
		
	}
	
	public void done() {
		overlaybkgThread = null;
	}

	@Override
	public void run() {
		
		while (overlaybkgThread != null) {
			
			resize();
			if (Main.thread == null) overlaybkgThread = null;
			
		}
		
	}
	
	void resize() {
		
		setSize(new Dimension(Main.window.getWidth()-1, Main.window.getHeight()));
		setLocation((Main.window.getWidth()/2)-(getWidth()/2), Main.window.getHeight()/2 - getHeight()/2 - 20);
		
	}
	
}
