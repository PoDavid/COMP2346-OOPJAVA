import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class AuthenticationSystem implements Hashing {
    private ArrayList<User> UserList;

    AuthenticationSystem() {
        UserList = new ArrayList<>();
    }

    public String getInput() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String inputLine = null;
        try {
            inputLine = input.readLine();
        } catch (IOException e) {
            System.err.println("Input ERROR.");
        }
        return inputLine;
    }

    public void authenticate() {
        System.out.println("Please enter your username:");
        String username = getInput();
        System.out.println("Please enter your password:");
        String password = getInput();
        boolean usernameMatch = false;
        for(User user : UserList){
            if(user.checkUsername(username)) {
                usernameMatch = true;
                user.checkPassword(password);
            }
        }
        if(!usernameMatch)
            System.out.println("User not found!");
    }

    public void addUserRecord() {
        System.out.println("Please enter your username:");
        String username = getInput();
        if (checkUsername(username)) {
            System.out.println("Please enter your password:");
            String password = getInput();
            if (checkPassword(password)) {
                System.out.println("Please re-enter your password:");
                String re_password = getInput();
                if (checkRePassword(password, re_password)) {
                    System.out.println("Please enter your full name:");
                    String fullname = getInput();
                    System.out.println("Please enter your email address:");
                    String emailaddress = getInput();
                    System.out.println("Please enter your phone number:");
                    String phonenumber = getInput();
                    String hashedPassword = hash(password);
                    User user = new User(username, hashedPassword, fullname, emailaddress, phonenumber);
                    UserList.add(user);
                    System.out.println("Record added successfully!");
                }
            }
        }
    }

    public void editUserRecord() {

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
            System.out.println("Your password has to fulfill: at least 6 characters, 1 small letter, 1 capital letter, 1 digit!");
            return false;
        }
    }

    private Boolean checkRePassword(String password, String re_password) {
        if (password.equals(re_password))
            return true;
        else {
            System.out.println("Passwords do not match, no user added!");
            return false;
        }
    }

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
}
