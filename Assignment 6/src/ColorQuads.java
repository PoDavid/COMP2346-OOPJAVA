import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Quads class, used to model a Quadrilateral in the DrawObject Editor Canvas.
 * @author Po Yat Ching David UID:3035372098
 */
public class ColorQuads implements Serializable {
    private ArrayList<Polygon> quads;
    private ArrayList<Color> colors;
    private ArrayList<Color> fillColors;

    /**
     * Instantiates a new Color quads.
     */
    ColorQuads(){
        quads = new ArrayList<>();
        colors = new ArrayList<>();
        fillColors = new ArrayList<>();
    }

    /**
     * Get quads array list.
     *
     * @return the quads array list
     */
    public ArrayList<Polygon> getQuads(){
        return quads;
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

    /**
     * Clear all the Arraylist in the class.
     */
    public void clear(){
        quads.clear();
        colors.clear();
        fillColors.clear();
    }
}
