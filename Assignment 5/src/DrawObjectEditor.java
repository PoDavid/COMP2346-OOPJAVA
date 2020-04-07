import java.awt.*;
import javax.swing.*;

public class DrawObjectEditor extends JFrame{

    public DrawObjectEditor() {
        super("Draw Object Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,500);
        setBackground(Color.gray);

        JPanel jp_draw = new JPanel();
        jp_draw.setSize(400,450);
        JPanel jp_buttons = new JPanel();
        getContentPane().add(BorderLayout.NORTH,jp_draw);

        jp_buttons.add(new JButton("Line"));
        jp_buttons.add(new JButton("Circle"));
        jp_buttons.add(new JButton("Triangle"));
        jp_buttons.add(new JButton("Quadrilateral"));
        jp_buttons.add(new JButton("Select"));
        jp_buttons.add(new JButton("Move"));
        jp_buttons.add(new JButton("Delete"));
        jp_buttons.add(new JButton("Copy"));
        jp_buttons.add(new JButton("Random Color"));
        jp_buttons.setLayout(new GridLayout(3,3,0,0));
        getContentPane().add(BorderLayout.SOUTH,jp_buttons);

        setVisible(true);
    }
}
