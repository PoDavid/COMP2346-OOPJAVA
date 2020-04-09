import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The Draw Object Editor class, used to model the Object Editor.
 * @author Po Yat Ching David UID:3035372098
 */
public class DrawObjectEditor extends JFrame {
    private DrawPanel jp_draw;
    private JButton jb_lin;
    private JButton jb_cir;
    private JButton jb_tri;
    private JButton jb_qua;
    private JButton jb_sel;
    private JButton jb_mov;
    private JButton jb_del;
    private JButton jb_cop;
    private JButton jb_ran;
    private boolean drawLine;
    private boolean drawCircle;
    private boolean drawTriangle;
    private boolean drawQuad;
    private boolean select;
    private boolean move;
    private boolean movePressed;
    private boolean first_click;
    private boolean second_click;
    private boolean third_click;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private int[]x=new int[4];
    private int[]y=new int[4];

    /**
     * Instantiates a new Draw object editor with title "Draw Object Editor".
     */
    public DrawObjectEditor() {
        super("Draw Object Editor");
    }

    /**
     * The entry point of the application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        DrawObjectEditor editor = new DrawObjectEditor();
        editor.go();
    }

    /**
     * The driver function of the Draw Object Editor.
     * Initialize the Editor with a JFrame, a JPanel and several JButtons.
     */
    public void go(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setBackground(Color.gray);

        jp_draw = new DrawPanel();
        jp_draw.setSize(400, 450);
        jp_draw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (drawLine)
                    drawLine(e);
                else if (drawCircle)
                    drawCircle(e);
                else if (drawTriangle)
                    drawTriangle(e);
                else if (drawQuad)
                    drawQuad(e);
                else if (select) {
                    select(e);
                }
            }
            public void mousePressed(MouseEvent e){
                if(move)
                    movePressed(e);
            }
            public void mouseReleased(MouseEvent e){
                if(movePressed)
                    moveReleased(e);
            }
        });
        JPanel jp_buttons = new JPanel();
        getContentPane().add(BorderLayout.CENTER, jp_draw);

        jb_lin = new JButton("Line");
        jb_lin.addActionListener(new LineListener());

        jb_cir = new JButton("Circle");
        jb_cir.addActionListener(new CircleListener());

        jb_tri = new JButton("Triangle");
        jb_tri.addActionListener(new TriangleListener());

        jb_qua = new JButton("Quadrilateral");
        jb_qua.addActionListener(new QuadrilateralListener());

        jb_sel = new JButton("Select");
        jb_sel.addActionListener(new SelectListener());

        jb_mov = new JButton("Move");
        jb_mov.addActionListener(new MoveListener());

        jb_del = new JButton("Delete");
        jb_del.addActionListener(new DeleteListener());

        jb_cop = new JButton("Copy");
        jb_cop.addActionListener(new CopyListener());

        jb_ran = new JButton("Random Color");
        jb_ran.addActionListener(new RandListener());

        jp_buttons.add(jb_lin);
        jp_buttons.add(jb_cir);
        jp_buttons.add(jb_tri);
        jp_buttons.add(jb_qua);
        jp_buttons.add(jb_sel);
        jp_buttons.add(jb_mov);
        jp_buttons.add(jb_del);
        jp_buttons.add(jb_cop);
        jp_buttons.add(jb_ran);

        jp_buttons.setLayout(new GridLayout(3, 3, 0, 0));
        getContentPane().add(BorderLayout.SOUTH, jp_buttons);

        jb_mov.setEnabled(false);
        jb_del.setEnabled(false);
        jb_cop.setEnabled(false);
        jb_ran.setEnabled(false);

        jb_mov.setBackground(Color.gray);
        jb_del.setBackground(Color.gray);
        jb_cop.setBackground(Color.gray);
        jb_ran.setBackground(Color.gray);

        setVisible(true);
    }

    /**
     * The Action listener for the Button Line.
     * Toggle the button when the button is pressed.
     */
    class LineListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_lin.setEnabled(false);
            jb_lin.setBackground(Color.gray);
            drawLine=true;
        }
    }

    /**
     * Draw a line when a mose click happens.
     * Calls the addLine() in DrawPanel class after collecting two points.
     * @param e the MoseEvent (Clicked)
     */
    public void drawLine(MouseEvent e){
        if(drawLine && !first_click){
            x1=e.getX();
            y1=e.getY();
            jp_draw.addDot(x1,y1);
            first_click=true;
        }
        else if(drawLine){
            x2=e.getX();
            y2=e.getY();
            drawLine=false;
            first_click=false;
            jb_lin.setEnabled(true);
            jb_lin.setBackground(null);
            jp_draw.addLine(x1,y1,x2,y2);
        }
    }

    /**
     * The Action listener for the Button Circle.
     * Toggle the button when the button is pressed.
     */
    class CircleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_cir.setEnabled(false);
            jb_cir.setBackground(Color.gray);
            drawCircle=true;
        }
    }

    /**
     * Draw a circle when a mose click happens.
     * Calls the addCircle () in DrawPanel class after collecting two points.
     * @param e the MoseEvent (Clicked)
     */
    public void drawCircle(MouseEvent e){
        if(drawCircle && !first_click){
            x1=e.getX();
            y1=e.getY();
            jp_draw.addDot(x1,y1);
            first_click=true;
        }
        else if(drawCircle){
            x2=e.getX();
            y2=e.getY();
            drawCircle=false;
            first_click=false;
            jb_cir.setEnabled(true);
            jb_cir.setBackground(null);
            jp_draw.addCircle(x1,y1,x2,y2);
        }
    }

    /**
     * The Action listener for the Button Triangle.
     * Toggle the button when the button is pressed.
     */
    class TriangleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_tri.setEnabled(false);
            jb_tri.setBackground(Color.gray);
            drawTriangle=true;
        }
    }

    /**
     * Draw a triangle when a mose click happens.
     * Calls the addTriangle () in DrawPanel class after collecting three points.
     * @param e the MoseEvent (Clicked)
     */
    public void drawTriangle(MouseEvent e){
        if(drawTriangle && !first_click ){
            x1=e.getX();
            y1=e.getY();
            jp_draw.addDot(x1,y1);
            x[0] = (int) x1;
            y[0] = (int) y1;
            first_click=true;
        }
        else if(drawTriangle && !second_click ){
            x1=e.getX();
            y1=e.getY();
            jp_draw.addDot(x1,y1);
            x[1]= (int) x1;
            y[1]= (int) y1;
            second_click=true;
        }
        else if (drawTriangle){
            x1=e.getX();
            y1=e.getY();
            x[2]= (int) x1;
            y[2]= (int) y1;
            drawTriangle=false;
            first_click=false;
            second_click=false;
            jb_tri.setEnabled(true);
            jb_tri.setBackground(null);
            jp_draw.addQuadrilateral(x,y,3);
        }
    }

    /**
     * The Action listener for the Button Quadrilateral.
     * Toggle the button when the button is pressed.
     */
    class QuadrilateralListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_qua.setEnabled(false);
            jb_qua.setBackground(Color.gray);
            drawQuad=true;
        }
    }

    /**
     * Draw a quadrilateral when a mose click happens.
     * Calls the addQuad () in DrawPanel class after collecting four points.
     * @param e the MoseEvent (Clicked)
     */
    public void drawQuad(MouseEvent e){
        if(drawQuad && !first_click ){
            x1=e.getX();
            y1=e.getY();
            jp_draw.addDot(x1,y1);
            x[0] = (int) x1;
            y[0] = (int) y1;
            first_click=true;
        }
        else if(drawQuad && !second_click ){
            x1=e.getX();
            y1=e.getY();
            jp_draw.addDot(x1,y1);
            x[1]= (int) x1;
            y[1]= (int) y1;
            second_click=true;
        }
        else if (drawQuad && !third_click ){
            x1=e.getX();
            y1=e.getY();
            jp_draw.addDot(x1,y1);
            x[2]= (int) x1;
            y[2]= (int) y1;
            third_click=true;
        }
        else if(drawQuad){
            x1=e.getX();
            y1=e.getY();
            x[3]= (int) x1;
            y[3]= (int) y1;
            drawQuad=false;
            first_click=false;
            second_click=false;
            third_click=false;
            jb_qua.setEnabled(true);
            jb_qua.setBackground(null);
            jp_draw.addQuadrilateral(x,y,4);
        }
    }

    /**
     * The Action listener for the Button Select.
     * Toggle the button when the button is pressed.
     */
    class SelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_sel.setEnabled(false);
            jb_sel.setBackground(Color.gray);
            select=true;
        }
    }

    /**
     * Get the coordinate of the mouse click.
     * Toggle related buttons if the click happens inside an object.
     * @param e the MouseEvent(Clicked)
     */
    public void select(MouseEvent e){
        x1=e.getX();
        y1=e.getY();
        if (jp_draw.checkContains(x1,y1)){
            select=false;
            jb_mov.setEnabled(true);
            jb_del.setEnabled(true);
            jb_cop.setEnabled(true);
            jb_ran.setEnabled(true);
            jb_mov.setBackground(null);
            jb_del.setBackground(null);
            jb_cop.setBackground(null);
            jb_ran.setBackground(null);
        }
    }

    /**
     * The Action listener for the Button Move.
     * Toggle the button when the button is pressed.
     */
    class MoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_sel.setEnabled(false);
            jb_sel.setBackground(Color.gray);
            move=true;

            jb_sel.setEnabled(true);
            jb_sel.setBackground(null);
            jb_mov.setEnabled(false);
            jb_del.setEnabled(false);
            jb_cop.setEnabled(false);
            jb_ran.setEnabled(false);
            jb_mov.setBackground(Color.gray);
            jb_del.setBackground(Color.gray);
            jb_cop.setBackground(Color.gray);
            jb_ran.setBackground(Color.gray);
        }
    }

    /**
     * Get the coordinate of the position of the mouse press.
     * Call the movePressed() function in DrawPanel Class.
     *
     * @param e the MouseEvent (Pressed)
     */
    public void movePressed(MouseEvent e){
        x1 = e.getX();
        y1 = e.getY();
        movePressed=jp_draw.movePressed(x1,y1);
    }

    /**
     * Get the coordinate of the position of the mouse release.
     * Call the moveReleased() function in DrawPanel Class.
     * @param e the MouseEvent (Released)
     */
    public void moveReleased(MouseEvent e){
        x1 = e.getX();
        y1 = e.getY();
        jp_draw.moveReleased(x1,y1);
        move=false;
        movePressed=false;

        jb_sel.setEnabled(true);
        jb_sel.setBackground(null);
        jb_mov.setEnabled(false);
        jb_del.setEnabled(false);
        jb_cop.setEnabled(false);
        jb_ran.setEnabled(false);
        jb_mov.setBackground(Color.gray);
        jb_del.setBackground(Color.gray);
        jb_cop.setBackground(Color.gray);
        jb_ran.setBackground(Color.gray);
    }

    /**
     * The Action listener for the Button Delete.
     * Toggle the button when the button is pressed.
     * Call the delete() function in the DrawlPanel class to delete the selected object
     */
    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_sel.setEnabled(false);
            jb_sel.setBackground(Color.gray);
            jp_draw.delete();

            jb_sel.setEnabled(true);
            jb_sel.setBackground(null);
            jb_mov.setEnabled(false);
            jb_del.setEnabled(false);
            jb_cop.setEnabled(false);
            jb_ran.setEnabled(false);
            jb_mov.setBackground(Color.gray);
            jb_del.setBackground(Color.gray);
            jb_cop.setBackground(Color.gray);
            jb_ran.setBackground(Color.gray);
        }
    }

    /**
     * The Action listener for the Button Copy.
     * Toggle the button when the button is pressed.
     * Call the copy() function in the DrawPanel class to copy the selected object.
     */
    class CopyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_sel.setEnabled(false);
            jb_sel.setBackground(Color.gray);
            jp_draw.copy();

            jb_sel.setEnabled(true);
            jb_sel.setBackground(null);
            jb_mov.setEnabled(false);
            jb_del.setEnabled(false);
            jb_cop.setEnabled(false);
            jb_ran.setEnabled(false);
            jb_mov.setBackground(Color.gray);
            jb_del.setBackground(Color.gray);
            jb_cop.setBackground(Color.gray);
            jb_ran.setBackground(Color.gray);
        }
    }

    /**
     * The Action listener for the Button Random.
     * Toggle the button when the button is pressed.
     * Calls the random() in DrawPanel Class to fill the selected object with random color
     */
    class RandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_sel.setEnabled(false);
            jb_sel.setBackground(Color.gray);
            jp_draw.random();

            jb_sel.setEnabled(true);
            jb_sel.setBackground(null);
            jb_mov.setEnabled(false);
            jb_del.setEnabled(false);
            jb_cop.setEnabled(false);
            jb_ran.setEnabled(false);
            jb_mov.setBackground(Color.gray);
            jb_del.setBackground(Color.gray);
            jb_cop.setBackground(Color.gray);
            jb_ran.setBackground(Color.gray);
        }
    }
}