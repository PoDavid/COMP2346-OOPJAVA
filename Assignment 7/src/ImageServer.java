import jdk.internal.util.xml.impl.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ImageServer {
    private AuthenticationSystem authSys;
    public static void main(String[] args) {
        ImageServer server = new ImageServer();
        server.go();
    }
    public void go(){
        authSys = new AuthenticationSystem();
//        authSys.addUserRecord();
//        authSys.addUserRecord();
//        authSys.addUserRecord();
//        authSys.saveRecord();
        authSys.loadRecord();
        ServerImageSharingSystem system = new ServerImageSharingSystem();
        system.go();
        launchServer();
    }
   private void launchServer(){
        try {
            ServerSocket ss = new ServerSocket(9000);
            System.out.println("Server Now Listening to Port 9000 ... ");
            while(true){
                Socket s = ss.accept();
                InputStreamReader is = new InputStreamReader(s.getInputStream());
                BufferedReader reader = new BufferedReader(is);
                PrintWriter writer = new PrintWriter(s.getOutputStream());

                String  username, hashedPassword;
                username = reader.readLine();
                System.out.println(username);

                hashedPassword = reader.readLine();
                System.out.println(hashedPassword);



                if (authSys.authenticate(username,hashedPassword)){
                    System.out.println("Authenticated");
                    writer.println("success");
                }
                else{
                    System.out.println("Login Fail");
                    writer.println("fail");
                }
                writer.flush();

                reader.close();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
