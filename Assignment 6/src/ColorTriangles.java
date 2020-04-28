import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Triangle class, used to model a Triangle in the DrawObject Editor Canvas.
 * @author Po Yat Ching David UID:3035372098
 */
public class ColorTriangles implements Serializable {
    private ArrayList<Polygon> triangles;
    private ArrayList<Color> colors;
    private ArrayList<Color> fillColors;

    /**
     * Instantiates a new triangles.
     */
    ColorTriangles(){
        triangles = new ArrayList<>();
        colors = new ArrayList<>();
        fillColors = new ArrayList<>();
    }

    /**
     * Get triangles array list.
     *
     * @return the triangles array list
     */
    public ArrayList<Polygon> getTriangles(){
        return triangles;
    }

    /**
     * Get border color array list.
     *
     * @return the border color array list
     */
    public ArrayList<Color> getColor(){
        return colors;
    }

    /**
     * Get fill color array list.
     *
     * @return the fill color array list
     */
    public ArrayList<Color> getFillColor(){
        return fillColors;
    }
}
