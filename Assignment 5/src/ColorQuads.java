import java.awt.*;
import java.util.ArrayList;

public class ColorQuads {
    private ArrayList<Polygon> quads;
    private ArrayList<Color> colors;
    private ArrayList<Color> fillColors;
    ColorQuads(){
        quads = new ArrayList<>();
        colors = new ArrayList<>();
        fillColors = new ArrayList<>();
    }
    public ArrayList<Polygon> getQuads(){
        return quads;
    }
    public ArrayList<Color> getColor(){
        return colors;
    }
    public ArrayList<Color> getFillColor(){
        return fillColors;
    }
}
