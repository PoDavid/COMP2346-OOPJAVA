import javax.swing.*;
import java.awt.*;

/**
 * The ServerImageSharingSystem class, used to simulate the Puzzle Game.
 * @author Po Yat Ching David UID:3035372098
 */
public class PeerImageSharingSystem extends JFrame {
    ImagePanel jp_image;
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
        setView();
    }

    private void setView(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(700, 750);

        jp_image = new ImagePanel();

        getContentPane().add(BorderLayout.NORTH, jp_image);

        pack();
    }
}