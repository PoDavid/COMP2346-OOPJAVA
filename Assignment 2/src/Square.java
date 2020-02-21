/**
 * The type Square, a subclass of Rectangle
 * Used to model Squares
 * @author Po Yat Ching 3035372098
 */
public class Square extends Rectangle{
    private int size;
    /**
     * Instantiates a new Square.
     * Initialize the 2D boolean ArrayList that represents a Square with dimesnion size*size
     * @param size the size of the Square
     */
    Square(int size){
        super(size,size);
        this.size = size;
    }
}
