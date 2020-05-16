import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ImagePeer {
    public static void main(String[] args) {
        ImagePeer peer = new ImagePeer();
        peer.go();
    }
    public void go(){
        String IP, username, password, hashedPassword;
        IP = JOptionPane.showInputDialog("Connect to server:");
        username = JOptionPane.showInputDialog("Username");
        password = JOptionPane.showInputDialog("Password");
        AuthenticationSystem authSys = new AuthenticationSystem();
        hashedPassword = authSys.hash(password);
        try{
            Socket sock = new Socket(IP, 9000);
            PrintWriter writer = new PrintWriter(sock.getOutputStream());
            InputStreamReader is = new InputStreamReader(sock.getInputStream());
            BufferedReader reader = new BufferedReader(is);

            writer.println(username);
            writer.println(hashedPassword);

            writer.flush();

            String authenticated = reader.readLine();
            if(authenticated.equals("success")){
                System.out.println("Login Successfully");
            }
            else{
                JOptionPane.showMessageDialog( null, "Login Fail" );
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        

        PeerImageSharingSystem system = new PeerImageSharingSystem();
        system.go();
    }
}
