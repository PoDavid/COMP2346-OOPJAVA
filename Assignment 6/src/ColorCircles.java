import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Circle class, used to model a Circle in the DrawObject Editor Canvas.
 * @author Po Yat Ching David UID:3035372098
 */
public class ColorCircles implements Serializable {
    private ArrayList<Ellipse2D.Double> circles;
    private ArrayList<Color> colors;
    private ArrayList<Color> fillColors;

    /**
     * Instantiates a new circles.
     */
    ColorCircles(){
        circles = new ArrayList<>();
        colors = new ArrayList<>();
        fillColors = new ArrayList<>();
    }

    /**
     * Get circle array list.
     *
     * @return the circle array list
     */
    public ArrayList<Ellipse2D.Double> getCircle(){
        return circles;
    }

    /**
     * Get border color array list.
     *
     * @return the board color array list
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
        circles.clear();
        colors.clear();
        fillColors.clear();
    }
}
