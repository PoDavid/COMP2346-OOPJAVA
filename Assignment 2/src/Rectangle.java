import java.util.ArrayList;

public class Rectangle extends Shape{
    private int width;
    private int height;

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
