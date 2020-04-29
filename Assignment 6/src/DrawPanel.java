import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * The Draw Panel class, used to model the Canvas in the Object Editor.
 * @author Po Yat Ching David UID:3035372098
 */
public class DrawPanel extends JPanel implements Serializable {
    private ColorLines lines = new ColorLines();
    private ColorCircles circles = new ColorCircles();
    private ColorTriangles triangles = new ColorTriangles();
    private ColorQuads quads = new ColorQuads();
    private Point p;
    private double move_x;
    private double move_y;
    private boolean select;
    private boolean movepressed;
    private int selected = -1;
    private int selectedObject;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        selected = -1;
        for (int i = 0; i < lines.getLine().size(); i++) {
            if (select && lines.getLine().get(i).contains(p)) {
                lines.getColor().set(i, Color.green);
                selected = 0;
                selectedObject = i;
                select = false;
            }
            g2.setColor(lines.getColor().get(i));
            g2.draw(lines.getLine().get(i));
        }
        for (int i = 0; i < circles.getCircle().size(); i++) {
            if (select && circles.getCircle().get(i).contains(p)) {
                circles.getColor().set(i, Color.green);
                selected = 1;
                selectedObject = i;
                select = false;
            }
            g2.setColor(circles.getFillColor().get(i));
            g2.fill(circles.getCircle().get(i));
            g2.setColor(circles.getColor().get(i));
            g2.draw(circles.getCircle().get(i));
        }
        for (int i = 0; i < triangles.getTriangles().size(); i++) {
            if (select && triangles.getTriangles().get(i).contains(p)) {
                triangles.getColor().set(i, Color.green);
                selected = 2;
                selectedObject = i;
                select = false;
            }
            g2.setColor(triangles.getFillColor().get(i));
            g2.fill(triangles.getTriangles().get(i));
            g2.setColor(triangles.getColor().get(i));
            g2.draw(triangles.getTriangles().get(i));
        }
        for (int i = 0; i < quads.getQuads().size(); i++) {
            if (select && quads.getQuads().get(i).contains(p)) {
                quads.getColor().set(i, Color.green);
                selected = 3;
                selectedObject = i;
                select = false;
            }
            g2.setColor(quads.getFillColor().get(i));
            g2.fill(quads.getQuads().get(i));
            g2.setColor(quads.getColor().get(i));
            g2.draw(quads.getQuads().get(i));
        }
    }

    /**
     * Add a black line in the specified position.
     *
     * @param x1 the x coordinate of the first point
     * @param y1 the y coordinate of the second point
     * @param x2 the x coordinate of the first point
     * @param y2 the y coordinate of the second point
     */
    public void addLine(double x1, double y1, double x2, double y2) {
        lines.getLine().remove(lines.getLine().size() - 1); //remove the dot
        lines.getColor().remove(lines.getColor().size() - 1);

        lines.getLine().add(new Line2D.Double(x1, y1, x2, y2));
        lines.getColor().add(Color.black);
        repaint();
    }

    /**
     * Add a dot in the specified position.
     *
     * @param x1 the x coordinate of the dot.
     * @param y1 the y coordinate of the dot.
     */
    public void addDot(double x1, double y1) {
        lines.getLine().add(new Line2D.Double(x1, y1, x1, y1));
        lines.getColor().add(Color.black);
        repaint();
    }

    /**
     * Add a circle in the specified position.
     *
     * @param x1 the x coordinate of the first click
     * @param y1 the y coordinate of the first click
     * @param x2 the x coordinate of the second click
     * @param y2 the y coordinate of the second click
     */
    public void addCircle(double x1, double y1, double x2, double y2) {
        lines.getLine().remove(lines.getLine().size() - 1); //remove the dot
        lines.getColor().remove(lines.getColor().size() - 1);
        double radius = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)) * 2;
        double centre_x = x1 - radius / 2;
        double centre_y = y1 - radius / 2;
        circles.getCircle().add(new Ellipse2D.Double((int) centre_x, (int) centre_y, (int) radius, (int) radius));
        circles.getColor().add(Color.black);
        circles.getFillColor().add(new Color(238, 238, 238));
        repaint();
    }

    /**
     * Add a triangle/quadrilateral in the specified position.
     *
     * @param x the list of x coordinate of the points.
     * @param y the list of y coordinate of the points.
     * @param n the the number of points (3 for triangle, 4 for quadrilateral).
     */
    public void addQuadrilateral(int[] x, int[] y, int n) {
        for (int i = 0; i < n - 1; i++) {
            lines.getLine().remove(lines.getLine().size() - 1);
            lines.getColor().remove(lines.getColor().size() - 1);
        }
        if (n == 3) {
            triangles.getTriangles().add(new Polygon(x, y, n));
            triangles.getColor().add(Color.black);
            triangles.getFillColor().add(new Color(238, 238, 238));
        } else if (n == 4) {
            quads.getQuads().add(new Polygon(x, y, n));
            quads.getColor().add(Color.black);
            quads.getFillColor().add(new Color(238, 238, 238));
        }
        repaint();
    }

    /**
     * Check if the given point contains in any of the object.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     * @return the boolean of whether the point contains in any of the object or not.
     */
    public boolean checkContains(double x, double y) {
        p = new Point((int) x, (int) y);
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

    /**
     * Check if the given point contains in the selected object.
     *
     * @param x the x
     * @param y the y
     * @return the boolean of whether the given point contains in the selected object or not.
     */
    public boolean movePressed(double x, double y) {
        p = new Point((int) x, (int) y);
        move_x = x;
        move_y = y;
        if (selected == 0) {
            movepressed = lines.getLine().get(selectedObject).contains(p);
        } else if (selected == 1) {
            movepressed = circles.getCircle().get(selectedObject).contains(p);
        } else if (selected == 2) {
            movepressed = triangles.getTriangles().get(selectedObject).contains(p);
        } else if (selected == 3) {
            movepressed = quads.getQuads().get(selectedObject).contains(p);
        }
        return movepressed;
    }

    /**
     * Move the selected object to the specified position.
     *
     * @param x the x coordinate of the new position.
     * @param y the y coordinate of the new position.
     */
    public void moveReleased(double x, double y) {
        if (selected == 0) {
            Line2D.Double newLine = lines.getLine().get(selectedObject);
            newLine.x1 += x - move_x;
            newLine.x2 += x - move_x;
            newLine.y1 += y - move_y;
            newLine.y2 += y - move_y;
            lines.getLine().set(selectedObject, newLine);
            lines.getColor().set(selectedObject, Color.black);
        } else if (selected == 1) {
            Ellipse2D.Double newCircle = circles.getCircle().get(selectedObject);
            newCircle.x += x - move_x;
            newCircle.y += y - move_y;
            circles.getCircle().set(selectedObject, newCircle);
            circles.getColor().set(selectedObject, Color.black);
        } else if (selected == 2) {
            Polygon oldTriangles = triangles.getTriangles().get(selectedObject);
            int[] newX = new int[3];
            int[] newY = new int[3];
            for (int i = 0; i < 3; i++) {
                newX[i] = oldTriangles.xpoints[i] + (int) (x - move_x);
                newY[i] = oldTriangles.ypoints[i] + (int) (y - move_y);
            }
            triangles.getTriangles().set(selectedObject, new Polygon(newX, newY, 3));
            triangles.getColor().set(selectedObject, Color.black);
        } else if (selected == 3) {
            Polygon oldQuads = quads.getQuads().get(selectedObject);
            int[] newX = new int[4];
            int[] newY = new int[4];
            for (int i = 0; i < 4; i++) {
                newX[i] = oldQuads.xpoints[i] + (int) (x - move_x);
                newY[i] = oldQuads.ypoints[i] + (int) (y - move_y);
            }
            quads.getQuads().set(selectedObject, new Polygon(newX, newY, 4));
            quads.getColor().set(selectedObject, Color.black);
        }
        repaint();
    }

    /**
     * Delete the selected object.
     */
    public void delete() {
        if (selected == 0) {
            lines.getLine().remove(selectedObject);
            lines.getColor().remove(selectedObject);
        } else if (selected == 1) {
            circles.getCircle().remove(selectedObject);
            circles.getColor().remove(selectedObject);
            circles.getFillColor().remove(selectedObject);
        } else if (selected == 2) {
            triangles.getTriangles().remove(selectedObject);
            triangles.getColor().remove(selectedObject);
            triangles.getFillColor().remove(selectedObject);
        } else if (selected == 3) {
            quads.getQuads().remove(selectedObject);
            quads.getColor().remove(selectedObject);
            quads.getFillColor().remove(selectedObject);
        }
        repaint();
    }

    /**
     * Copy the selected object.
     */
    public void copy() {
        if (selected == 0) {
            Line2D.Double line = lines.getLine().get(selectedObject);
            Line2D.Double copy_line = new Line2D.Double(line.x1 += 30, line.y1 += 30, line.x2 += 30, line.y2 += 30);
            lines.getColor().set(selectedObject, Color.black);
            lines.getLine().add(copy_line);
            lines.getColor().add(Color.black);
        } else if (selected == 1) {
            Ellipse2D.Double circle = circles.getCircle().get(selectedObject);
            Ellipse2D.Double copy_circle = new Ellipse2D.Double(circle.x + 30, circle.y + 30, circle.width, circle.height);
            circles.getColor().set(selectedObject, Color.black);
            circles.getCircle().add(copy_circle);
            circles.getColor().add(Color.black);
            circles.getFillColor().add(circles.getFillColor().get(selectedObject));
        } else if (selected == 2) {
            Polygon triangle = triangles.getTriangles().get(selectedObject);
            int[] x = new int[3];
            int[] y = new int[3];
            for (int i = 0; i < 3; i++) {
                x[i] = triangle.xpoints[i] + 30;
                y[i] = triangle.ypoints[i] + 30;
            }
            Polygon copy_triangle = new Polygon(x, y, 3);
            triangles.getColor().set(selectedObject, Color.black);
            triangles.getTriangles().add(copy_triangle);
            triangles.getColor().add(Color.black);
            triangles.getFillColor().add(triangles.getFillColor().get(selectedObject));
        } else if (selected == 3) {
            Polygon quad = quads.getQuads().get(selectedObject);
            int[] x = new int[4];
            int[] y = new int[4];
            for (int i = 0; i < 4; i++) {
                x[i] = quad.xpoints[i] + 30;
                y[i] = quad.ypoints[i] + 30;
            }
            Polygon copy_quad = new Polygon(x, y, 4);
            quads.getColor().set(selectedObject, Color.black);
            quads.getQuads().add(copy_quad);
            quads.getColor().add(Color.black);
            quads.getFillColor().add(quads.getFillColor().get(selectedObject));
        }
        repaint();
    }

    /**
     * Fill the selected object with a random color.
     */
    public void random() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randColor = new Color(r, g, b);
        if (selected == 1) {
            circles.getFillColor().set(selectedObject, randColor);
            circles.getColor().set(selectedObject, Color.black);
        } else if (selected == 2) {
            triangles.getFillColor().set(selectedObject, randColor);
            triangles.getColor().set(selectedObject, Color.black);
        } else if (selected == 3) {
            quads.getFillColor().set(selectedObject, randColor);
            quads.getColor().set(selectedObject, Color.black);
        }
        repaint();
    }

    public void export(String filepath) {
        try {
            OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(filepath));
            String textline;
            for (int i = 0; i < lines.getLine().size(); i++) {
                textline = "line;";
                textline += lines.getLine().get(i).x1 + ";";
                textline += lines.getLine().get(i).y1 + ";";
                textline += lines.getLine().get(i).x2 + ";";
                textline += lines.getLine().get(i).y2 + ";";
                textline += "0;0;0\n";
                w.write(textline);
            }
            for (int i = 0; i < circles.getCircle().size(); i++) {
                textline = "circle;";
                textline += circles.getCircle().get(i).x + ";";
                textline += circles.getCircle().get(i).y + ";";
                textline += circles.getCircle().get(i).height + ";";
                textline += circles.getFillColor().get(i).getRed() + ";";
                textline += circles.getFillColor().get(i).getGreen() + ";";
                textline += circles.getFillColor().get(i).getBlue() + ";\n";
                w.write(textline);
            }
            for (int i = 0; i < triangles.getTriangles().size(); i++) {
                textline = "triangle;";
                for (int j = 0; j < 3; j++) {
                    textline += triangles.getTriangles().get(i).xpoints[j] + ";";
                    textline += triangles.getTriangles().get(i).ypoints[j] + ";";
                }
                textline += triangles.getFillColor().get(i).getRed() + ";";
                textline += triangles.getFillColor().get(i).getGreen() + ";";
                textline += triangles.getFillColor().get(i).getBlue() + ";\n";
                w.write(textline);
            }
            for (int i = 0; i < quads.getQuads().size(); i++) {
                textline = "quadrilateral;";
                for (int j = 0; j < 4; j++) {
                    textline += quads.getQuads().get(i).xpoints[j] + ";";
                    textline += quads.getQuads().get(i).ypoints[j] + ";";
                }
                textline += quads.getFillColor().get(i).getRed() + ";";
                textline += quads.getFillColor().get(i).getGreen() + ";";
                textline += quads.getFillColor().get(i).getBlue() + ";\n";
                w.write(textline);
            }
            w.close();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public void importAscii(String filepath) {
        try {
            FileInputStream fis = new FileInputStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            lines.clear();
            circles.clear();
            triangles.clear();
            quads.clear();
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer stok = new StringTokenizer(line,";");
                switch (stok.nextToken()) {
                    case "line": {
                        Line2D.Double newLine = new Line2D.Double(Double.parseDouble(stok.nextToken()), Double.parseDouble(stok.nextToken()), Double.parseDouble(stok.nextToken()), Double.parseDouble(stok.nextToken()));
                        lines.getLine().add(newLine);
                        lines.getColor().add(Color.black);
                        break;
                    }
                    case "circle": {
                        double x = Double.parseDouble(stok.nextToken());
                        double y = Double.parseDouble(stok.nextToken());
                        double radius = Double.parseDouble(stok.nextToken());
                        Ellipse2D.Double newCircle = new Ellipse2D.Double(x, y, radius, radius);
                        circles.getCircle().add(newCircle);
                        circles.getColor().add(Color.black);
                        Color fillColor = new Color(Integer.parseInt(stok.nextToken()),Integer.parseInt(stok.nextToken()),Integer.parseInt(stok.nextToken()));
                        circles.getFillColor().add(fillColor);
                        break;
                    }
                   case "triangle": {
                        int[] x = new int[3];
                        int[] y = new int[3];
                        for(int i=0;i<3;i++){
                            x[i] = Integer.parseInt(stok.nextToken());
                            y[i] = Integer.parseInt(stok.nextToken());
                        }
                        Polygon newTriangle = new Polygon(x,y,3);
                        triangles.getTriangles().add(newTriangle);
                        triangles.getColor().add(Color.black);
                        Color fillColor = new Color(Integer.parseInt(stok.nextToken()),Integer.parseInt(stok.nextToken()),Integer.parseInt(stok.nextToken()));
                        triangles.getFillColor().add(fillColor);
                        break;
                    }
                    case "quadrilateral": {
                        int[] x = new int[4];
                        int[] y = new int[4];
                        for(int i=0;i<4;i++){
                            x[i] = Integer.parseInt(stok.nextToken());
                            y[i] = Integer.parseInt(stok.nextToken());
                        }
                        Polygon newQuad = new Polygon(x,y,4);
                        quads.getQuads().add(newQuad);
                        quads.getColor().add(Color.black);
                        Color fillColor = new Color(Integer.parseInt(stok.nextToken()),Integer.parseInt(stok.nextToken()),Integer.parseInt(stok.nextToken()));
                        quads.getFillColor().add(fillColor);
                        break;
                    }
                }
            }
            repaint();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}
