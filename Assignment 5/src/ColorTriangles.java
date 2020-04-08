import java.awt.*;
import java.util.ArrayList;

public class ColorTriangles {
    private ArrayList<Polygon> triangles;
    private ArrayList<Color> colors;
    ColorTriangles(){
        triangles = new ArrayList<>();
        colors = new ArrayList<>();
    }
    public ArrayList<Polygon> getTriangles(){
        return triangles;
    }
    public ArrayList<Color> getColor(){
        return colors;
    }
}
