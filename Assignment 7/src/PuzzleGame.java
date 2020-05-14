import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * The PuzzleGame class, used to simulate the Puzzle Game.
 * @author Po Yat Ching David UID:3035372098
 */
public class PuzzleGame extends JFrame{
    private ImagePanel jp_image;
    private Boolean firstClick=false;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    /**
     * Instantiates a new Puzzle game.
     */
    public PuzzleGame(){
        super("Puzzle Game");
    }

    /**
     * The entry point of the application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        PuzzleGame game = new PuzzleGame();
        game.go();
    }

    /**
     * The driven program of the Puzzle Game
     */
    public void go(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 700);

        File selectedFile = chooseFile();
        if (selectedFile==null){
            System.out.println("No File Selected.");
            return;
        }
        jp_image = new ImagePanel(selectedFile.getAbsolutePath());
        jp_image.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!firstClick) {
                    x1 = e.getX();
                    y1 = e.getY();
                    int offsetX = (jp_image.getWidth() - 500) / 2;
                    int offsetY = (jp_image.getHeight() - 500) / 2;
                    x1 -= offsetX;
                    y1 -= offsetY;
                    if (x1 <= 500 && x1 >= 0 && y1 <= 500 && y1 >= 0)
                        firstClick = true;
                }
                else {
                    x2 = e.getX();
                    y2 = e.getY();
                    int offsetX = (jp_image.getWidth() - 500) / 2;
                    int offsetY = (jp_image.getHeight() - 500) / 2;
                    x2 -= offsetX;
                    y2 -= offsetY;
                    if (x2 <= 500 && x2 >= 0 && y2 <= 500 && y2 >= 0) {
                        firstClick = false;
                        jp_image.swapBlock(x1, y1, x2, y2);
                        if (jp_image.checkWin()) {
                            JOptionPane.showMessageDialog(null, "You Win!", "Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
        getContentPane().add(BorderLayout.NORTH, jp_image);

        JPanel jp_buttons = new JPanel();

        JButton jb_new = new JButton("Load New Image");
        jb_new.addActionListener(new NewListener());

        JButton jb_save = new JButton("Save Game");
        jb_save.addActionListener(new SaveListener());

        JButton jb_load = new JButton("Load Game");
        jb_load.addActionListener(new LoadListener());

        JButton jb_show = new JButton("Show Original Image");
        jb_show.addActionListener(new ShowListener());

        jp_buttons.add(jb_new);
        jp_buttons.add(jb_save);
        jp_buttons.add(jb_load);
        jp_buttons.add(jb_show);

        jp_buttons.setLayout(new GridLayout(1, 3, 0,0));
        jp_buttons.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(BorderLayout.SOUTH, jp_buttons);
        pack();
        setVisible(true);
        
    }

    /**
     * Present a file chooser to ask for an image file.
     *
     * @return the selected image file. File
     */
    public File chooseFile(){
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jfc.showOpenDialog(null);

        File selectedFile = null;
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = jfc.getSelectedFile();

        }
        return selectedFile;
    }

    /**
     * The Action listener for the Button Load New Image.
     * Present file chooser and call the newImage() in jp_image.
     */
    class NewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFile = chooseFile();
            if (selectedFile==null){
                System.out.println("No File Selected.");
                return;
            }
            jp_image.newImage(selectedFile.getAbsolutePath());
        }
    }

    /**
     * The Action listener for the Button Save Game.
     * Present file chooser and call the saveImage() in jp_image.
     */
    class SaveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFile = chooseFile();
            if (selectedFile==null){
                System.out.println("No File Selected.");
                return;
            }
            jp_image.saveImage(selectedFile.getPath());
        }
    }

    /**
     * The Action listener for the Button Load Game.
     * Present file chooser and call the loadImage() in jp_image.
     */
    class LoadListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFile = chooseFile();
            if (selectedFile==null){
                System.out.println("No File Selected.");
                return;
            }
            jp_image.loadImage(selectedFile.getPath());
        }
    }

    /**
     * The Action listener for the Button Show Original Game.
     * Present file chooser and call the showImage() in jp_image.
     */
    class ShowListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            jp_image.showImage();
        }
    }
}
