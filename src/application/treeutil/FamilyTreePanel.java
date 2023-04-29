package treelinecalculationsalgorithm;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class FamilyTreePanel extends JPanel
{
    private final ArrayList<Line> lines = new ArrayList<Line>();

    public void addLine(int x1, int x2, int x3, int x4) 
    {
        lines.add(new Line(x1 ,x2, x3, x4));        
        repaint();
    }

    public void addLine(Line line)
    {
        lines.add(new Line(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY()));        
        repaint();
    }

    public void removeLine(Line targetLine)
    {
        for(int i = 0; i < lines.size(); i++)
        {
            if(lines.get(i).getStartX() == targetLine.getStartX() && lines.get(i).getStartY() == targetLine.getStartY() && lines.get(i).getEndX() == targetLine.getEndX() && lines.get(i).getEndY() == targetLine.getEndY())
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
        for (Line line : lines) {
            g.setColor(line.color);
            g.drawLine(line.x1, line.y1, line.x2, line.y2);
        }
    }
}