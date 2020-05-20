import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ImagePeer {
    public static void main(String[] args) {
        ImagePeer peer = new ImagePeer();
        peer.go();
    }
    public void go(){
        PeerImageSharingSystem system = new PeerImageSharingSystem();
        system.go();
        String IP, username, password, hashedPassword;
        IP = JOptionPane.showInputDialog("Connect to server:");
        username = JOptionPane.showInputDialog("Username");
        password = JOptionPane.showInputDialog("Password");
        AuthenticationSystem authSys = new AuthenticationSystem();
        hashedPassword = authSys.hash(password);
        try{
            Socket sock = new Socket(IP, 9000);
            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

            out.writeObject(username);
            out.writeObject(hashedPassword);
            out.flush();

            String authenticated = (String) in.readObject();
            System.out.println("Server message is " + authenticated);
            if(authenticated.equals("success")){
                System.out.println("Login Successfully");
                system.setVisible(true);
                System.out.println("Start Loading Image");
                while(true){
                    String msg = (String) in.readObject();
                    switch(msg){
                        case "Load Image":{
                            ArrayList<BufferedImage> imglist = new ArrayList<>();
                            List<byte[]> list = (List<byte[]>) in.readObject();
                            for (byte[] bytes : list) {
                                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                                imglist.add(ImageIO.read(bis));
                             }
                            system.jp_image.setImg(imglist);
                            break;
                        }
                        case "Swap Block":{
                            int x1 = (Integer) in.readObject();
                            int y1 = (Integer)in.readObject();
                            int x2 = (Integer)in.readObject();
                            int y2 = (Integer)in.readObject();
                            System.out.println("X - Y " + x1 + ", " + y1 + ", " + x2 + ", " + y2);
                            system.jp_image.swapBlock(x1,y1,x2,y2);
                            break;
                        }
                    }
                }
            }

            else{
                JOptionPane.showMessageDialog( null, "Login Fail" );
                System.out.println("Terminating");
                System.exit(0);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        



    }
}
