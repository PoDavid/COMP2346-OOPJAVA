import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * The Authentication system class, used to model the authentication system.
 * @author Po Yat Ching David UID:3035372098
 */
public class AuthenticationSystem implements Hashing {
    private ArrayList<User> UserList;
    private BufferedReader input;

    /**
     * Instantiates a new Authentication system.
     */
    AuthenticationSystem() {
        UserList = new ArrayList<>();
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Gets User input.
     *
     * @return the choice of the user 0-3
     */
    public String getInput() {
        String inputLine = null;
        try {
            inputLine = input.readLine();
        } catch (IOException e) {
            System.err.println("Input ERROR.");
        }
        return inputLine;
    }

    /**
     * Perform the authenticate option by the given username and password
     *
     * @return the authenticated user's username or null if username and password does not match
     */
    public String authenticate() {
        System.out.println("Please enter your username:");
        String username = getInput();
        System.out.println("Please enter your password:");
        String password = getInput();
        boolean usernameMatch = false;
        boolean authenticated = false;
        for(User user : UserList){
            if(user.checkUsername(username)) {
                usernameMatch = true;
                if(user.checkPassword(hash(password)))
                    authenticated = true;
            }
        }
        if(!usernameMatch)
            System.out.println("User not found!");
        if (authenticated)
            return username;
        else
            return null;
    }

    /**
     * Perform the add new user record option.
     * Get the related information from user
     * Create a new user record by the given user information
     */
    public void addUserRecord() {
        System.out.println("Please enter your username:");
        String username = getInput();
        if (checkUsername(username)) {
            while(true) {
                System.out.println("Please enter your password:");
                String password = getInput();
                if (checkPassword(password)) {
                    System.out.println("Please re-enter your password:");
                    String re_password = getInput();
                    if (checkRePassword(password, re_password)) {
                        String hashedPassword = hash(password);
                        User user = new User(username, hashedPassword);
                        UserList.add(user);
                        System.out.println("Record added successfully!");
                    }
                    else{
                        System.out.println("Passwords do not match, no user added!");
                    }
                    break;
                }
            }
        }
    }

    private Boolean checkUsername(String username) {
        for (User user : UserList) {
            if (user.checkUsername(username)) {
                System.out.println("The username is already taken!");
                return false;
            }
        }
        return true;
    }

    private Boolean checkPassword(String password) {
        if (password.length() >= 6 && password.matches("(?=.*[0-9]).*") && password.matches("(?=.*[a-z]).*") && password.matches("(?=.*[A-Z]).*"))
            return true;
        else {
            System.out.println("Your password has to fulfil: at least 6 characters, 1 small letter, 1 capital letter, 1 digit!");
            return false;
        }
    }

    private Boolean checkRePassword(String password, String re_password) {
        return password.equals(re_password);
    }

    /**
     * Hash the given plaintext password to hashed password.
     *
     * @param password the plaintext password
     * @return the hashed password
     */
    public String hash(String password) {
        MessageDigest messageDigest;
        String hashedPassword = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] messageDigestMD5 = messageDigest.digest();
            StringBuilder stringBuffer = new StringBuilder();
            for (byte bytes : messageDigestMD5) {
                stringBuffer.append(String.format("%02x", bytes & 0xff));
            }
            hashedPassword = stringBuffer.toString();
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }
        return hashedPassword;
    }
    public void saveRecord(){
        try{
            File outputFile = new File("User.txt");
            OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(outputFile));
            String text;
            for(User user : UserList){
                text="";
                text+="username:"+user.getUsername()+";hashPassword:"+user.getHashedPassword()+'\n';
                System.out.println("Writing: " + text);
                w.write(text);
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadRecord(){
        try{
            File inputFile = new File("User.txt");
            InputStreamReader r = new InputStreamReader(new FileInputStream(inputFile));
            BufferedReader br = new BufferedReader(r);
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer stok = new StringTokenizer(line, ";");
                String username = null, hashPassword = null;
                username = stok.nextToken().substring(9);
                hashPassword = stok.nextToken().substring(13);
                UserList.add(new User(username,hashPassword));
            }
            r.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for(User user : UserList){
//            System.out.println(user.getUsername() + "       " + user.getHashedPassword());
//        }
    }
}
