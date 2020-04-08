import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
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
    boolean first_click=false;
    boolean second_click=false;
    boolean third_click=false;
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
            public void mousePressed(MouseEvent e) {
                //Draw Line
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
                //Draw Circle
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
                //Draw Triangle
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
                    jb_qua.setEnabled(true);
                    jb_qua.setBackground(null);
                    jp_draw.addQuadrilateral(x,y,3);
                }
                //Draw Quadrilateral
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
        //jb_sel.addActionListener(new LineListener());

        jb_mov = new JButton("Move");
        //jb_mov.addActionListener(new LineListener());

        jb_del = new JButton("Delete");
        //jb_del.addActionListener(new LineListener());

        jb_cop = new JButton("Copy");
        //jb_cop.addActionListener(new LineListener());

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
    class CircleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_cir.setEnabled(false);
            jb_cir.setBackground(Color.gray);
            drawCircle=true;
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
    class QuadrilateralListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jb_qua.setEnabled(false);
            jb_qua.setBackground(Color.gray);
            drawQuad=true;
        }
    }
}