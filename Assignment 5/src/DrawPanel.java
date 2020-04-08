import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private ArrayList<Line2D.Double> lines = new ArrayList<>();
    private ArrayList<Ellipse2D.Double> circles = new ArrayList<>();
    private ArrayList<Polygon> triangles = new ArrayList<>();
    private ArrayList<Polygon> quads = new ArrayList<>();
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Line2D line : lines) {
            g2.draw(line);
        }
        for (Ellipse2D circle : circles) {
            g2.draw(circle);
        }
        for (Polygon triangle : triangles) {
            g2.draw(triangle);
        }
        for (Polygon quad : quads) {
            g2.draw(quad);
        }
    }
    public void addLine(double x1,double y1, double x2, double y2){
        lines.remove(lines.size()-1); //remove the dot
        lines.add(new Line2D.Double(x1,y1,x2,y2));
        repaint();
    }
    public void addDot(double x1,double y1){
        lines.add(new Line2D.Double(x1,y1,x1,y1));
        repaint();
    }
    public void addCircle(double x1,double y1, double x2, double y2){
        lines.remove(lines.size()-1); //remove the dot
        double radius = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1))*2;
        double centre_x = x1-radius/2;
        double centre_y = y1-radius/2;
        circles.add(new Ellipse2D.Double(centre_x,centre_y,radius,radius));
        repaint();
    }
    public void addQuadrilateral(int[]x,int[]y,int n){
        for(int i=0;i<n-1;i++){
            lines.remove(lines.size()-1);
        }
        if(n==3)
            triangles.add(new Polygon(x,y,n));
        else if(n==4)
            quads.add(new Polygon(x,y,n));
        repaint();
    }
}
