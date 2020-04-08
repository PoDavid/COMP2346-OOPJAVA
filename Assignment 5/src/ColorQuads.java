import java.awt.*;
import java.util.ArrayList;

public class ColorQuads {
    private ArrayList<Polygon> quads;
    private ArrayList<Color> colors;
    ColorQuads(){
        quads = new ArrayList<>();
        colors = new ArrayList<>();
    }
    public ArrayList<Polygon> getQuads(){
        return quads;
    }
    public ArrayList<Color> getColor(){
        return colors;
    }
}
