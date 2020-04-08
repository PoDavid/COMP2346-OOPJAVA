import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private ArrayList<Line2D.Double> lines = new ArrayList<>();
    private ArrayList<Ellipse2D.Double> circles = new ArrayList<>();
    private ArrayList<Polygon> triangles = new ArrayList<>();
    private ArrayList<Polygon> quads = new ArrayList<>();
    private Point p;
    private boolean select;
    private int selected=-1;
    private int selectedObject;
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        selected=-1;
        for(int i=0;i<lines.size();i++){
            if (select&&lines.get(i).contains(p)) {
                g2.setColor(Color.green);
                selected = 0;
                selectedObject = i;
                select=false;
            }
            else
                g2.setColor(Color.black);
            g2.draw(lines.get(i));
        }
        for (int i=0;i<circles.size();i++) {
            if (select&&circles.get(i).contains(p)) {
                g2.setColor(Color.green);
                selected = 1;
                selectedObject = i;
                select=false;
            }
            else
                g2.setColor(Color.black);
            g2.draw(circles.get(i));
        }
        for (int i=0;i<triangles.size();i++) {
            if (select&&triangles.get(i).contains(p)) {
                g2.setColor(Color.green);
                selected = 2;
                selectedObject = i;
                select=false;
            }
            else
                g2.setColor(Color.black);
            g2.draw(triangles.get(i));
        }
        for (int i=0;i<quads.size();i++) {
            if (select&&quads.get(i).contains(p)) {
                g2.setColor(Color.green);
                selected = 3;
                selectedObject = i;
                select=false;
            }
            else
                g2.setColor(Color.black);
            g2.draw(quads.get(i));
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
    public boolean checkContains(double x,double y){
        p = new Point((int)x,(int)y);
        select = true;
        repaint();
        for (Line2D.Double line : lines) {
            if (line.contains(p)) {
                return true;
            }
        }
        for (Ellipse2D.Double circle : circles) {
            if (circle.contains(p))
                return true;
        }
        for (Polygon triangle : triangles) {
            if (triangle.contains(p))
                return true;
        }
        for (Polygon quad : quads) {
            if (quad.contains(p))
                return true;
        }
        return false;
    }
    public void delete(){
        if (selected==0){
            lines.remove(selectedObject);
        }
        else if (selected==1){
            circles.remove(selectedObject);
        }
        else if (selected==2){
            triangles.remove(selectedObject);
        }
        else if (selected==3){
            quads.remove(selectedObject);
        }
        repaint();
    }
    public boolean getSelected(){
        return selected != -1;
    }
}
