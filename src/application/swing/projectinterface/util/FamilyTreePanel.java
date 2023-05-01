package application.swing.projectinterface.util;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FamilyTreePanel extends JPanel
{

	private static final long serialVersionUID = 5269739480373011929L;
	private final ArrayList<Line> lines = new ArrayList<Line>();

    public void addLine(int x1, int x2, int x3, int x4) 
    {
        lines.add(new Line(x1 ,x2, x3, x4));        
        repaint();
    }

    public void addLine(Line line)
    {
        lines.add(line);        
        repaint();
    }

    public void removeLine(Line line)
    {
    	if (line == null) return;
        for(int i = 0; i < lines.size(); i++)
        {
            if(lines.get(i).getStartX() == line.getStartX() && lines.get(i).getStartY() == line.getStartY() && lines.get(i).getEndX() == line.getEndX() && lines.get(i).getEndY() == line.getEndY())
            {
                lines.remove(i);
                repaint();
            }
        }
    }

    public void clearLines() 
    {
        lines.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Line line : lines) {
        	if (line != null) {
        		g2.setStroke(new BasicStroke(4));
                g2.setColor(line.color);
                g2.drawLine(line.x1, line.y1, line.x2, line.y2);
        	}
        	
        }
    }
    public void resize() {
    	setSize(getMinimumSize());
    }
}