package application.swing.projectinterface.util;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Line extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2420361232648121884L;
	public int x1; 
    public final int y1;
    public int x2;
    public final int y2;   
    public final Color color;

    public Line(int x1, int y1, int x2, int y2) 
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = Color.white;
        setBounds(x1, y1, x2-x1, y2-y1);

    }   
    
    public int getStartX()
    {
        return x1;
    }

    public int getStartY()
    {
        return y1;
    }

    public int getEndX()
    {
        return x2;
    }

    public int getEndY()
    {
        return y2;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}