package Q2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.*;
import java.util.List;

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
     * The Array List that stores all 25 image blocks.
     */
    ArrayList<BufferedImage> imgList = new ArrayList<>();

    /**
     * Instantiates a new Image panel.
     *
     * @param name the filename of the image
     */
    ImagePanel(String name) {
        super(true);
        try {
            img = ImageIO.read(new File(name));
            this.setPreferredSize(new Dimension(500, 500));
            loadImage();
            randomize();
        } catch (Exception e) {
            System.out.println("Error occurs, invalid image shutting down the program.");
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        int x = (this.getWidth() - 500) / 2;
        int y = (this.getHeight() - 500) / 2;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                g.drawImage(imgList.get(i * 5 + j), x + i * 100, y + j * 100, 100, 100, null);
            }
        }
    }

    /**
     * Load the image to the ArrayList imgList. Diving it into 25 blocks.
     */
    public void loadImage() {
        imgList.clear();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                imgList.add(img.getSubimage(i * img.getWidth() / 5, j * img.getHeight() / 5, img.getWidth() / 5, img.getHeight() / 5));
            }
        }
        randomize();
        repaint();
    }

    /**
     * Randomize the 25 blocks of sub Image in Arraylist imgList.
     */
    public void randomize() {
        Collections.shuffle(imgList);
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

        firstBlock = x1/100*5 + y1/100;
        secondBlock = x2/100*5 + y2/100;
        Collections.swap(imgList,firstBlock,secondBlock);
        repaint();
    }

    /**
     * Check if the Puzzle is completed.
     *
     * @return the boolean of whether the Puzzle is completed or not.
     */
    public boolean checkWin(){
        BufferedImage block;
        BufferedImage ansBlock;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                block = imgList.get(i*5+j);
                ansBlock = img.getSubimage(i * img.getWidth() / 5, j * img.getHeight() / 5, img.getWidth() / 5, img.getHeight() / 5);
                if(!compareBlock(block,ansBlock))
                    return false;
            }
        }
        return true;
    }

    /**
     * Check if two blocks are the same
     *
     * @param blockA the image of block A
     * @param blockB the image of block B
     * @return the boolean of whether the two blocks are the same.
     */
    public boolean compareBlock(BufferedImage blockA, BufferedImage blockB){
        int count = 0;
        for (int x = 0; x < blockA.getWidth(); x++) {
            for (int y = 0; y < blockA.getHeight(); y++) {
                if (blockA.getRGB(x, y) != blockB.getRGB(x, y))
                    count++;
            }
        }
        return count < 1000;

    }

    /**
     * Save the current image and the 25 blocks subimage to the filepath.
     *
     * @param filepath the filepath of the selected save location.
     */
    public void saveImage(String filepath){
        try {
            List<byte[]> list = new ArrayList<>();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filepath));

            //25 blocks image
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(imgList.get(i*5+j), "jpg", bos);
                    byte []data = bos.toByteArray();
                    list.add(data);
                }
            }
            //Answer image
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", bos);
            byte []data = bos.toByteArray();
            list.add(data);

            out.writeObject(list);
         } catch (IOException e) {
               e.printStackTrace();
         }
    }

    /**
     * Load the image and the 25 blocks of subimage from the filepath
     *
     * @param filepath the filepath of the file to be loaded.
     */
    public void loadImage(String filepath){
        ArrayList<BufferedImage> newImgList = new ArrayList<>();
        BufferedImage oldImg = img;
        ArrayList<BufferedImage> oldList = (ArrayList<BufferedImage>) imgList.clone();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath));

            List<byte[]> list = (List<byte[]>) in.readObject();
            for (byte[] bytes : list) {
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                newImgList.add(ImageIO.read(bis));
             }
            imgList.clear();
            img = newImgList.get(newImgList.size()-1);
            newImgList.remove(newImgList.size()-1);
            imgList = newImgList;
            repaint();
        } catch (Exception e) {
            System.out.println("Error occurs, image failed to load, retaining old image.");
            img = oldImg;
            imgList = oldList;
        }
    }

    /**
     * Show the original image.
     */
    void showImage(){
        JFrame popUpImage = new JFrame();
        popUpImage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popUpImage.setSize(510, 540);
        JPanel jp_ansImg = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(img,0,0,500,500,null);
            }
        };
        popUpImage.add(jp_ansImg);
        popUpImage.setVisible(true);
    }
}