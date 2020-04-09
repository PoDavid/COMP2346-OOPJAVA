import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawObjectEditor extends JFrame {
    DrawPanel jp_draw;
    JButton jb_lin;
    JButton jb_cir;
    JButton jb_tri;
    JButton jb_qua;
    JButton jb_sel;
    JButton jb_mov;
    JButton jb_del;
    JButton jb_cop;
    JButton jb_ran;
    boolean drawLine;
    boolean drawCircle;
    boolean drawTriangle;
    boolean drawQuad;
    boolean select;
    boolean move;
    boolean movePressed;
    boolean first_click;
    boolean second_click;
    boolean third_click;
    double x1;
    double y1;
    double x2;
    double y2;
    int[]x=new int[4];
    int[]y=new int[4];
    public DrawObjectEditor() {
        super("Draw Object Editor");
    }
    public static void main(String[] args) {
        DrawObjectEditor editor = new DrawObjectEditor();
        editor.go();
    }
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
        //jb_ran.addActionListener(new LineListener());

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
    class LineListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_lin.setEnabled(false);
            jb_lin.setBackground(Color.gray);
            drawLine=true;
        }
    }
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
    class CircleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_cir.setEnabled(false);
            jb_cir.setBackground(Color.gray);
            drawCircle=true;
        }
    }
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
    class TriangleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_tri.setEnabled(false);
            jb_tri.setBackground(Color.gray);
            drawTriangle=true;
        }
    }
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
    class QuadrilateralListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_qua.setEnabled(false);
            jb_qua.setBackground(Color.gray);
            drawQuad=true;
        }
    }
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
    class SelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_sel.setEnabled(false);
            jb_sel.setBackground(Color.gray);
            select=true;
        }
    }
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
    public void movePressed(MouseEvent e){
        x1 = e.getX();
        y1 = e.getY();
        movePressed=jp_draw.movePressed(x1,y1);
    }
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
}