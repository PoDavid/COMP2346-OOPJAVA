import java.awt.*;
import java.awt.geom.Line2D;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Line class, used to model a CLine in the DrawObject Editor Canvas.
 * @author Po Yat Ching David UID:3035372098
 */
public class ColorLines implements Serializable {
    private ArrayList<Line2D.Double> lines;
    private ArrayList<Color> colors;

    /**
     * Instantiates a new lines.
     */
    ColorLines(){
        lines = new ArrayList<>();
        colors = new ArrayList<>();
    }

    /**
     * Get line array list.
     *
     * @return the line array list
     */
    public ArrayList<Line2D.Double> getLine(){
        return lines;
    }

    /**
     * Get border color array list.
     *
     * @return the border color array list
     */
    public ArrayList<Color> getColor(){
        return colors;
    }

    public void clear(){
        lines.clear();
        colors.clear();
    }
}
