package application.swing.projectinterface.actions;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import application.swing.main.Main;

public class Export implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		BufferedImage img = new BufferedImage(Main.treePanel.getWidth(), Main.treePanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		Main.treePanel.paint(g);
		
		try {
	        ImageIO.write(img, "png", new File("C:\\Users\\samth\\OneDrive\\Documents\\tree.png"));
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		
	}

}
