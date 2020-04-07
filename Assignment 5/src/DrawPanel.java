import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private ArrayList<Line2D.Double> lines = new ArrayList<>();
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Line2D line : lines) {
            g2.draw(line);
        }
    }
    public void addLine(double x1,double y1, double x2, double y2){
        lines.remove(lines.size()-1); //remove the dot
        lines.add(new Line2D.Double(x1,y1,x2,y2));
        repaint();
    }
    public void drawDot(double x1,double y1){
        lines.add(new Line2D.Double(x1,y1,x1,y1));
        repaint();
    }
}
