import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class ColorCircles {
    private ArrayList<Ellipse2D.Double> circles;
    private ArrayList<Color> colors;
    private ArrayList<Color> fillColors;
    ColorCircles(){
        circles = new ArrayList<>();
        colors = new ArrayList<>();
        fillColors = new ArrayList<>();
    }
    public ArrayList<Ellipse2D.Double> getCircle(){
        return circles;
    }
    public ArrayList<Color> getColor(){
        return colors;
    }
    public ArrayList<Color> getFillColor(){
        return fillColors;
    }
}
