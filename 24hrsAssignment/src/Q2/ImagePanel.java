package Q2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.*;
import java.util.List;

class ImagePanel extends JPanel {

    BufferedImage img;
    ArrayList<BufferedImage> imgList = new ArrayList<>();

    ImagePanel(String name) {
        super(true);
        try {
            img = ImageIO.read(new File(name));
            this.setPreferredSize(new Dimension(500, 500));
            loadImage();
            randomize();
        } catch (Exception e) {
            e.printStackTrace();
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

    public void randomize() {
        Collections.shuffle(imgList);
    }

    public void newImage(String name) {
        try {
            img = ImageIO.read(new File(name));
            loadImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void swapBlock(int x1,int y1, int x2, int y2){
        int firstBlock;
        int secondBlock;

        firstBlock = x1/100*5 + y1/100;
        secondBlock = x2/100*5 + y2/100;
        Collections.swap(imgList,firstBlock,secondBlock);
        repaint();
    }
    public boolean checkWin(){
        BufferedImage block;
        BufferedImage ansBlock;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                block = imgList.get(i*5+j);
                ansBlock = img.getSubimage(i * img.getWidth() / 5, j * img.getHeight() / 5, img.getWidth() / 5, img.getHeight() / 5);
                if(!compareBlock(block,ansBlock)) {
                    System.out.println("Not the same");
                    return false;
                }
                else{
                    System.out.println("The same");
                }
            }
        }
        return true;
    }
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
    public void loadImage(String filepath){
        ArrayList<BufferedImage> newImgList = new ArrayList<>();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath));

            List<byte[]> list = (List<byte[]>) in.readObject();
            for (byte[] bytes : list) {
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                newImgList.add(ImageIO.read(bis));
             }

        } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
        }
        imgList.clear();
        img = newImgList.get(newImgList.size()-1);
        newImgList.remove(newImgList.size()-1);
        imgList = newImgList;
        repaint();
    }
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