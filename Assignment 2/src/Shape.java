import java.util.ArrayList;

/**
 * The type Shape, used to model any 2D Shape
 * @author Po Yat Ching 3035372098
 */
public class Shape {
    protected ArrayList<ArrayList<Boolean>> shape;

    /**
     * Instantiates a new Shape that contains an empty 2D Boolean ArrayList.
     */
    Shape(){
        this.shape = new ArrayList<>();
    }
    /**
     * Convert the shape to a String
     * Converts True to * and False to space
     * Add \n to end of each row
     */
    public String toString(){
        //Find the right most True position
        int height = 0;
        int width = 0;
        for(int i=0;i<this.shape.size();i++){
            for(int j=0;j<this.shape.get(i).size();j++){
                if(this.shape.get(i).get(j)) {
                    if (height <= i)
                        height = i+1;
                    if (width <= j)
                        width = j+1;
                }
            }
        }
        //Construct the String
        StringBuilder output= new StringBuilder();
        for (int i=0;i<height;i++) {
            for(int j=0;j<width;j++){
                if(this.shape.get(i).get(j))
                    output.append("*");
                else
                    output.append(" ");
            }
            output.append("\n");
        }
        return output.substring(0, output.length() - 1);
    }

    /**
     * Get the int area of the shape.
     *
     * @return the number of * in the 2D ArrayList(shape)
     */
    public int getArea(){
        int size=0;
        for (ArrayList<Boolean> row : shape) {
            for (Boolean cell : row) {
                if (cell)
                    size += 1;
            }
        }
        return size;

    }

    /**
     * Intersect the current Shape (this) with Shape s.
     *
     * @param s the Shape to be intersect with current Shape
     * @return the intersection of the two Shapes
     */
    public Shape intersect(Shape s){
        //Create new shape for intersection
        Shape intersection = new Shape();
        int height = Math.max(this.shape.size(), s.shape.size());
        int width = Math.max(this.shape.get(0).size(), s.shape.get(0).size());
        for(int i=0; i < height; i++) {
            ArrayList<Boolean> row = new ArrayList<>();
            for(int j=0; j < width; j++) {
                row.add(false);
            }
            intersection.shape.add(row);
        }
        //Intersect operation
        for(int i=0; i<intersection.shape.size(); i++) {
            for (int j=0; j<intersection.shape.get(i).size(); j++) {
                if(!(i>=s.shape.size() || j>=s.shape.get(i).size()||i>=this.shape.size() || j>=this.shape.get(i).size()))
                    intersection.shape.get(i).set(j,this.shape.get(i).get(j) && s.shape.get(i).get(j));
            }
        }
        return intersection;
    }

    /**
     * Union the current Shape (this) with Shape s.
     *
     * @param s the Shape to be intersect with current Shape
     * @return the union of the two Shapes
     */
    public Shape union(Shape s){
        //Create new shape for union
        Shape union_result = new Shape();
        int height = Math.max(this.shape.size(), s.shape.size());
        int width = 0;
        for (ArrayList<Boolean> row : this.shape) {
            if (row.size() > width)
                width = row.size();
        }
        for (ArrayList<Boolean> row : s.shape) {
            if (row.size() > width)
                width = row.size();
        }
        for(int i=0; i < height; i++) {
            ArrayList<Boolean> row = new ArrayList<>();
            for(int j=0; j < width; j++) {
                row.add(false);
            }
            union_result.shape.add(row);
        }
        //Union operation
        for(int i=0; i<union_result.shape.size(); i++) {
            for (int j=0; j<union_result.shape.get(i).size(); j++) {
                if(i<this.shape.size()&&j<this.shape.get(i).size()&&i<s.shape.size()&&j<s.shape.get(i).size())
                    union_result.shape.get(i).set(j,this.shape.get(i).get(j)||s.shape.get(i).get(j));
                else if(i<this.shape.size()&&j<this.shape.get(i).size())
                    union_result.shape.get(i).set(j,this.shape.get(i).get(j));
                else if(i<s.shape.size()&&j<s.shape.get(i).size())
                    union_result.shape.get(i).set(j,s.shape.get(i).get(j));
            }
        }
        return union_result;
    }
}
