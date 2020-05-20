import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The ImagePanel class, used to model the canvas for displaying the Image Puzzle.
 * @author Po Yat Ching David UID:3035372098
 */
class ImagePanel extends JPanel {

    /**
     * The Original Image.
     */
    BufferedImage img;
    /**
     * The Array List that stores all 100 image blocks.
     */
    ArrayList<BufferedImage> imgList = new ArrayList<>();

    ImagePanel(){
        img = new BufferedImage(10,10, BufferedImage.TYPE_INT_ARGB);
        this.setPreferredSize(new Dimension(700, 700));
        for(int x = 0; x < img.getWidth(); x++) {
            for(int y = 0; y < img.getHeight(); y++) {
                img.setRGB(x, y, Color.white.getRGB());
            }
        }
        loadImage();
    }
    /**
     * Instantiates a new Image panel.
     *
     * @param name the filename of the image
     */
    ImagePanel(String name) {
        super(true);
        try {
            img = ImageIO.read(new File(name));
            this.setPreferredSize(new Dimension(700, 700));
            loadImage();
        } catch (Exception e) {
            System.out.println("Error occurs, invalid image shutting down the program.");
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        int x = (this.getWidth() - 700) / 2;
        int y = (this.getHeight() - 700) / 2;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawImage(imgList.get(i * 10 + j), x + i * 70, y + j * 70, 70, 70, null);
            }
        }
    }

    /**
     * Load the image to the ArrayList imgList. Diving it into 25 blocks.
     */
    public void loadImage() {
        imgList.clear();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                imgList.add(img.getSubimage(i * img.getWidth() / 10, j * img.getHeight() / 10, img.getWidth() / 10, img.getHeight() / 10));
            }
        }
        repaint();
    }

    /**
     * Load a new image from the given file name.
     *
     * @param name the filename of the new image.
     */
    public void newImage(String name) {
        BufferedImage oldImg = img;
        ArrayList<BufferedImage> oldList = (ArrayList<BufferedImage>) imgList.clone();
        try {
            img = ImageIO.read(new File(name));
            loadImage();
        } catch (Exception e) {
            System.out.println("Error occurs, retaining old image");
            img = oldImg;
            imgList = oldList;
        }

    }

    /**
     * Swap two sub Image blocks.
     *
     * @param x1 the x coordinate of the first block.
     * @param y1 the y coordinate of the first block.
     * @param x2 the x coordinate of the second block.
     * @param y2 the y coordinate of the second block.
     */
    public void swapBlock(int x1,int y1, int x2, int y2){
        int firstBlock;
        int secondBlock;

        System.out.println("Swapping");
        System.out.println("X - Y " + x1 + ", " + y1 + ", " + x2 + ", " + y2);
        firstBlock = x1/70*10 + y1/70;
        secondBlock = x2/70*10 + y2/70;
        Collections.swap(imgList,firstBlock,secondBlock);
        ImageServer.updatePeer(x1, y1, x2, y2);
        repaint();
    }

    public ArrayList<BufferedImage> getImage(){
        return imgList;
    }

    public void setImg(ArrayList<BufferedImage> newImg){
        imgList = newImg;
        repaint();
    }
}