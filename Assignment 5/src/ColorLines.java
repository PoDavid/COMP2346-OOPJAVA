import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.awt.*;
public class ColorLines {
    private ArrayList<Line2D.Double> lines;
    private ArrayList<Color> colors;
    private ArrayList<Color> fillColors;
    ColorLines(){
        lines = new ArrayList<>();
        colors = new ArrayList<>();
        fillColors = new ArrayList<>();
    }
    public ArrayList<Line2D.Double> getLine(){
        return lines;
    }
    public ArrayList<Color> getColor(){
        return colors;
    }
    public ArrayList<Color> getFillColor(){
        return fillColors;
    }
}
