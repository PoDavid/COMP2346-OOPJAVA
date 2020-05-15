
public class ImageServer {
    public static void main(String[] args) {
        ImageServer server = new ImageServer();
        server.go();
    }
    public void go(){
        AuthenticationSystem authSys = new AuthenticationSystem();
//        authSys.addUserRecord();
//        authSys.addUserRecord();
//        authSys.addUserRecord();
//        authSys.saveRecord();
        authSys.loadRecord();
        ImageSharingServer server = new ImageSharingServer();
        server.go();
    }

}
