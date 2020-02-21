import java.util.ArrayList;

public class Diamond extends Shape{
    private int size;
    Diamond(int size){
        super();
        this.size = size;
        //Create Empty Board
        for(int i=0; i < size*2-1; i++) {
            ArrayList<Boolean> row = new ArrayList<>();
            for(int j=0; j < size*2-1; j++) {
                row.add(false);
            }
            shape.add(row);
        }
        //Upper Triangle
        int x=size-1;
        int y=size-1;
        for(int i=0;i<size;i++){
            for(int j=x; j<y+1;j++){
                shape.get(i).set(j,true);
            }
            x--;
            y++;
        }
        //Lower Triangle
        x=1;
        y=2*size-2;
        for(int i=size;i<2*size-1;i++){
            for(int j=x; j<y;j++){
                shape.get(i).set(j,true);
            }
            x++;
            y--;
        }
    }
}
