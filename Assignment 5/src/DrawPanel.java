import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private ColorLines lines = new ColorLines();
    private ColorCircles circles = new ColorCircles();
    private ColorTriangles triangles = new ColorTriangles();
    private ColorQuads quads = new ColorQuads();
    private Point p;
    private boolean select;
    private int selected=-1;
    private int selectedObject;
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        selected=-1;
        for(int i=0;i<lines.getLine().size();i++){
            if (select&&lines.getLine().get(i).contains(p)) {
                lines.getColor().set(i,Color.green);
                selected = 0;
                selectedObject = i;
            }
            g2.setColor(lines.getColor().get(i));
            g2.draw(lines.getLine().get(i));
        }
        for (int i=0;i<circles.getCircle().size();i++) {
            if (select&&circles.getCircle().get(i).contains(p)) {
                circles.getColor().set(i,Color.green);
                selected = 1;
                selectedObject = i;
            }
            g2.setColor(circles.getColor().get(i));
            g2.draw(circles.getCircle().get(i));
        }
        for (int i=0;i<triangles.getTriangles().size();i++) {
            if (select&&triangles.getTriangles().get(i).contains(p)) {
                triangles.getColor().set(i,Color.green);
                selected = 2;
                selectedObject = i;
            }
            g2.setColor(triangles.getColor().get(i));
            g2.draw(triangles.getTriangles().get(i));
        }
        for (int i=0;i<quads.getQuads().size();i++) {
            if (select&&quads.getQuads().get(i).contains(p)) {
                quads.getColor().set(i,Color.green);
                selected = 3;
                selectedObject = i;
            }
            g2.setColor(quads.getColor().get(i));
            g2.draw(quads.getQuads().get(i));
        }
    }
    public void addLine(double x1,double y1, double x2, double y2){
        lines.getLine().remove(lines.getLine().size()-1); //remove the dot
        lines.getColor().remove(lines.getColor().size()-1);

        lines.getLine().add(new Line2D.Double(x1,y1,x2,y2));
        lines.getColor().add(Color.black);
        repaint();
    }
    public void addDot(double x1,double y1){
        lines.getLine().add(new Line2D.Double(x1,y1,x1,y1));
        lines.getColor().add(Color.black);
        repaint();
    }
    public void addCircle(double x1,double y1, double x2, double y2){
        lines.getLine().remove(lines.getLine().size()-1); //remove the dot
        lines.getColor().remove(lines.getColor().size()-1);
        double radius = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1))*2;
        double centre_x = x1-radius/2;
        double centre_y = y1-radius/2;
        circles.getCircle().add(new Ellipse2D.Double(centre_x,centre_y,radius,radius));
        circles.getColor().add(Color.black);
        repaint();
    }
    public void addQuadrilateral(int[]x,int[]y,int n){
        for(int i=0;i<n-1;i++){
            lines.getLine().remove(lines.getLine().size()-1);
            lines.getColor().remove(lines.getColor().size()-1);
        }
        if(n==3){
            triangles.getTriangles().add(new Polygon(x,y,n));
            triangles.getColor().add(Color.black);
        }
        else if(n==4) {
            quads.getQuads().add(new Polygon(x,y,n));
            quads.getColor().add(Color.black);
        }
        repaint();
    }
    public boolean checkContains(double x,double y){
        p = new Point((int)x,(int)y);
        select = true;
        repaint();
        for (Line2D.Double line : lines.getLine()) {
            if (line.contains(p)) {
                return true;
            }
        }
        for (Ellipse2D.Double circle : circles.getCircle()) {
            if (circle.contains(p))
                return true;
        }
        for (Polygon triangle : triangles.getTriangles()) {
            if (triangle.contains(p))
                return true;
        }
        for (Polygon quad : quads.getQuads()) {
            if (quad.contains(p))
                return true;
        }
        return false;
    }
    public void delete(){
        if (selected==0){
            lines.getLine().remove(selectedObject);
            lines.getColor().remove(selectedObject);
        }
        else if (selected==1){
            circles.getCircle().remove(selectedObject);
            circles.getColor().remove(selectedObject);
        }
        else if (selected==2){
            triangles.getTriangles().remove(selectedObject);
            triangles.getColor().remove(selectedObject);
        }
        else if (selected==3){
            quads.getQuads().remove(selectedObject);
            quads.getColor().remove(selectedObject);
        }
        select=false;
        repaint();
    }
    public void copy(){
        if (selected==0){
            Line2D.Double line = lines.getLine().get(selectedObject);
            Line2D.Double copy_line = new Line2D.Double(line.x1+=30,line.y1+=30,line.x2+=30,line.y2+=30);
            lines.getColor().set(selectedObject,Color.black);
            lines.getLine().add(copy_line);
            lines.getColor().add(Color.black);
        }
        else if (selected==1){
            Ellipse2D.Double circle = circles.getCircle().get(selectedObject);
            Ellipse2D.Double copy_circle = new Ellipse2D.Double(circle.x+30,circle.y+30,circle.width,circle.height);
            circles.getColor().set(selectedObject,Color.black);
            circles.getCircle().add(copy_circle);
            circles.getColor().add(Color.black);
        }
        else if (selected==2){
            Polygon triangle = triangles.getTriangles().get(selectedObject);
            int[] x = new int[3];
            int[] y = new int[3];
            for(int i=0;i<3;i++){
                x[i]=triangle.xpoints[i]+30;
                y[i]=triangle.ypoints[i]+30;
            }
            Polygon copy_triangle = new Polygon(x,y,3);
            triangles.getColor().set(selectedObject,Color.black);
            triangles.getTriangles().add(copy_triangle);
            triangles.getColor().add(Color.black);
        }
        else if (selected==3){
            Polygon quad = quads.getQuads().get(selectedObject);
            int[] x = new int[4];
            int[] y = new int[4];
            for(int i=0;i<4;i++){
                x[i]=quad.xpoints[i]+30;
                y[i]=quad.ypoints[i]+30;
            }
            Polygon copy_quad = new Polygon(x,y,4);
            quads.getColor().set(selectedObject,Color.black);
            quads.getQuads().add(copy_quad);
            quads.getColor().add(Color.black);
        }
        select=false;
        repaint();
    }
}
