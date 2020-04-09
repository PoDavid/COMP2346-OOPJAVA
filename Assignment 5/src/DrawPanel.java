import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class DrawPanel extends JPanel {
    private ColorLines lines = new ColorLines();
    private ColorCircles circles = new ColorCircles();
    private ColorTriangles triangles = new ColorTriangles();
    private ColorQuads quads = new ColorQuads();
    private Point p;
    private double move_x;
    private double move_y;
    private boolean select;
    private  boolean movepressed;
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
                select=false;
            }
            g2.setColor(lines.getColor().get(i));
            g2.draw(lines.getLine().get(i));
        }
        for (int i=0;i<circles.getCircle().size();i++) {
            if (select&&circles.getCircle().get(i).contains(p)) {
                circles.getColor().set(i,Color.green);
                selected = 1;
                selectedObject = i;
                select=false;
            }
            g2.setColor(circles.getFillColor().get(i));
            g2.fill(circles.getCircle().get(i));
            g2.setColor(circles.getColor().get(i));
            g2.draw(circles.getCircle().get(i));
        }
        for (int i=0;i<triangles.getTriangles().size();i++) {
            if (select&&triangles.getTriangles().get(i).contains(p)) {
                triangles.getColor().set(i,Color.green);
                selected = 2;
                selectedObject = i;
                select=false;
            }
            g2.setColor(triangles.getFillColor().get(i));
            g2.fill(triangles.getTriangles().get(i));
            g2.setColor(triangles.getColor().get(i));
            g2.draw(triangles.getTriangles().get(i));
        }
        for (int i=0;i<quads.getQuads().size();i++) {
            if (select&&quads.getQuads().get(i).contains(p)) {
                quads.getColor().set(i,Color.green);
                selected = 3;
                selectedObject = i;
                select=false;
            }
            g2.setColor(quads.getFillColor().get(i));
            g2.fill(quads.getQuads().get(i));
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
        circles.getFillColor().add(new Color(238,238,238));
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
            triangles.getFillColor().add(new Color(238,238,238));
        }
        else if(n==4) {
            quads.getQuads().add(new Polygon(x,y,n));
            quads.getColor().add(Color.black);
            quads.getFillColor().add(new Color(238,238,238));
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
    public boolean movePressed(double x, double y){
        p = new Point((int)x,(int)y);
        move_x=x;
        move_y=y;
        if(selected==0){
            movepressed=lines.getLine().get(selectedObject).contains(p);
        }
        else if(selected==1){
            movepressed=circles.getCircle().get(selectedObject).contains(p);
        }
        else if(selected==2){
            movepressed=triangles.getTriangles().get(selectedObject).contains(p);
        }
        else if(selected==3){
            movepressed=quads.getQuads().get(selectedObject).contains(p);
        }
        return movepressed;
    }
    public void moveReleased(double x,double y){
        if (selected==0){
            Line2D.Double newLine = lines.getLine().get(selectedObject);
            newLine.x1+=x-move_x;
            newLine.x2+=x-move_x;
            newLine.y1+=y-move_y;
            newLine.y2+=y-move_y;
            lines.getLine().set(selectedObject,newLine);
            lines.getColor().set(selectedObject,Color.black);
        }
        else if (selected==1){
            Ellipse2D.Double newCircle = circles.getCircle().get(selectedObject);
            newCircle.x+=x-move_x;
            newCircle.y+=y-move_y;
            circles.getCircle().set(selectedObject,newCircle);
            circles.getColor().set(selectedObject,Color.black);
        }
        else if (selected==2){
            Polygon oldTriangles = triangles.getTriangles().get(selectedObject);
            int[]newX=new int[3];
            int[]newY=new int[3];
            for(int i=0;i<3;i++){
                newX[i]=oldTriangles.xpoints[i]+(int)(x-move_x);
                newY[i]=oldTriangles.ypoints[i]+(int)(y-move_y);
            }
            triangles.getTriangles().set(selectedObject,new Polygon(newX,newY,3));
            triangles.getColor().set(selectedObject,Color.black);
        }
        else if (selected==3){
            Polygon oldQuads = quads.getQuads().get(selectedObject);
            int[]newX=new int[4];
            int[]newY=new int[4];
            for(int i=0;i<4;i++){
                newX[i]=oldQuads.xpoints[i]+(int)(x-move_x);
                newY[i]=oldQuads.ypoints[i]+(int)(y-move_y);
            }
            quads.getQuads().set(selectedObject,new Polygon(newX,newY,4));
            quads.getColor().set(selectedObject,Color.black);
        }
        repaint();
    }
    public void delete(){
        if (selected==0){
            lines.getLine().remove(selectedObject);
            lines.getColor().remove(selectedObject);
        }
        else if (selected==1){
            circles.getCircle().remove(selectedObject);
            circles.getColor().remove(selectedObject);
            circles.getFillColor().remove(selectedObject);
        }
        else if (selected==2){
            triangles.getTriangles().remove(selectedObject);
            triangles.getColor().remove(selectedObject);
            triangles.getFillColor().remove(selectedObject);
        }
        else if (selected==3){
            quads.getQuads().remove(selectedObject);
            quads.getColor().remove(selectedObject);
            quads.getFillColor().remove(selectedObject);
        }
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
            circles.getFillColor().add(circles.getFillColor().get(selectedObject));
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
            triangles.getFillColor().add(triangles.getFillColor().get(selectedObject));
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
            quads.getFillColor().add(quads.getFillColor().get(selectedObject));
        }
        repaint();
    }
}
