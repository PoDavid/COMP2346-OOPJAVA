import java.util.ArrayList;

/**
 * The type Rectangle, a subclass of Shape
 * Used to model Rectangles
 * @author Po Yat Ching 3035372098
 */
public class Rectangle extends Shape{
    private int width;
    private int height;

    /**
     * Instantiates a new Rectangle.
     * Initialize the 2D boolean ArrayList that represents a Rectangle with dimesnion width * height
     * @param width  the width of the Rectangle
     * @param height the height of the Rectangle
     */
    Rectangle(int width, int height){
        super();
        this.width = width;
        this.height = height;

        for(int i=0; i < height; i++) {
            ArrayList<Boolean> row = new ArrayList<>();
            for(int j=0; j < width; j++) {
                row.add(true);
            }
            shape.add(row);
        }
    }

}
