import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ImageServer {
    private AuthenticationSystem authSys;
    static ArrayList<ObjectOutputStream> peerOS = new ArrayList<>();
    static ServerImageSharingSystem system;
    public static void main(String[] args) {
        ImageServer server = new ImageServer();
        server.go();
    }

    public void go() {
        authSys = new AuthenticationSystem();
//        authSys.addUserRecord();
//        authSys.addUserRecord();
//        authSys.addUserRecord();
//        authSys.saveRecord();
        authSys.loadRecord();
        system = new ServerImageSharingSystem();
        system.go();
        launchServer();
    }

    private void launchServer() {
        try {
            ServerSocket ss = new ServerSocket(9000);
            System.out.println("Server Now Listening to Port 9000 ... ");
            while (true) {
                Socket s = ss.accept();
                ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                String username, hashedPassword;
                username = (String) in.readObject();
                System.out.println(username);

                hashedPassword = (String) in.readObject();
                System.out.println(hashedPassword);

                if (authSys.authenticate(username, hashedPassword)) {
                    System.out.println("Authenticated");
                    out.writeObject("success");
                    peerOS.add(out);
                    loadImg();
                } else {
                    System.out.println("Login Fail");
                    out.writeObject("fail");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
   public static void updatePeer(int x1, int y1, int x2, int y2) {
       System.out.println("X - Y " + x1 + ", " + y1 + ", " + x2 + ", " + y2);
        try {
           for (ObjectOutputStream out : peerOS) {
               out.writeObject("Swap Block");
               out.writeObject(x1);
               out.writeObject(y1);
               out.writeObject(x2);
               out.writeObject(y2);
               out.flush();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
       public static void loadImg(){
        ArrayList<BufferedImage> imgList = system.jp_image.getImage();
        System.out.println("Load Image" + imgList.size());
        try {
            List<byte[]> list = new ArrayList<>();
            for(BufferedImage img : imgList){
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(img, "jpg", bos);
                    byte []data = bos.toByteArray();
                    list.add(data);
            }
            for (ObjectOutputStream peerO : peerOS) {
                peerO.writeObject("Load Image");
                peerO.writeObject(list);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
   }
}

