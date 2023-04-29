package treelinecalculationsalgorithm;

import java.awt.Color;
import javax.swing.JComponent;

public class Line extends JComponent
{
    final int x1; 
    final int y1;
    final int x2;
    final int y2;   
    final Color color;

    public Line(int x1, int y1, int x2, int y2) 
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = Color.black;
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
}