import java.awt.*;
import java.util.ArrayList;

public class ColorTriangles {
    private ArrayList<Polygon> triangles;
    private ArrayList<Color> colors;
    private ArrayList<Color> fillColors;
    ColorTriangles(){
        triangles = new ArrayList<>();
        colors = new ArrayList<>();
        fillColors = new ArrayList<>();
    }
    public ArrayList<Polygon> getTriangles(){
        return triangles;
    }
    public ArrayList<Color> getColor(){
        return colors;
    }
    public ArrayList<Color> getFillColor(){
        return fillColors;
    }
}
