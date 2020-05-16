import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * The ServerImageSharingSystem class, used to simulate the Puzzle Game.
 * @author Po Yat Ching David UID:3035372098
 */
public class PeerImageSharingSystem extends JFrame {
    private ImagePanel jp_image;
    private Boolean firstClick = false;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private MouseAdapter mouseAdapter;
    /**
     * Instantiates a new PeerImageSharingSystem.
     */
    public PeerImageSharingSystem() {
        super("Image Peer");
    }

    /**
     * The driven program of the Puzzle Game
     */
    public void go() {
        setMouseAdapter();
        setView();
    }

    private void setMouseAdapter(){
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!firstClick) {
                    x1 = e.getX();
                    y1 = e.getY();
                    int offsetX = (jp_image.getWidth() - 700) / 2;
                    int offsetY = (jp_image.getHeight() - 700) / 2;
                    x1 -= offsetX;
                    y1 -= offsetY;
                    if (x1 <= 700 && x1 >= 0 && y1 <= 700 && y1 >= 0)
                        firstClick = true;
                } else {
                    x2 = e.getX();
                    y2 = e.getY();
                    int offsetX = (jp_image.getWidth() - 700) / 2;
                    int offsetY = (jp_image.getHeight() - 700) / 2;
                    x2 -= offsetX;
                    y2 -= offsetY;
                    if (x2 <= 700 && x2 >= 0 && y2 <= 700 && y2 >= 0) {
                        firstClick = false;
                        jp_image.swapBlock(x1, y1, x2, y2);
                    }
                }
            }
        };
    }


    private void setView(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(700, 750);

        File selectedFile = chooseFile();
        if (selectedFile == null) {
            System.out.println("No File Selected.");
            return;
        }

        jp_image = new ImagePanel(selectedFile.getAbsolutePath());

        jp_image.addMouseListener(mouseAdapter);

        getContentPane().add(BorderLayout.NORTH, jp_image);

        JPanel jp_buttons = new JPanel();

        JButton jb_new = new JButton("Load another image");
        jb_new.addActionListener(new NewListener());

        jp_buttons.add(jb_new);

        jp_buttons.setLayout(new GridLayout(1, 1, 0, 0));
        getContentPane().add(BorderLayout.SOUTH, jp_buttons);
        pack();
        setVisible(true);
    }


    /**
     * Present a file chooser to ask for an image file.
     *
     * @return the selected image file. File
     */
    public File chooseFile() {
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
            if (selectedFile == null) {
                System.out.println("No File Selected.");
                return;
            }
            jp_image.newImage(selectedFile.getAbsolutePath());
        }
    }
}