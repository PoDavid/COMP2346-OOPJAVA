import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
                        System.out.println("Please enter your full Name:");
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
                    else{
                        System.out.println("Passwords do not match, no user added!");
                    }
                    break;
                }
            }
        }
    }

    /**
     * Perform the edit user record's information option.
     * Perform authentication using the user's provided username and password.
     * If authenticated, allow modification of the user record.
     */
    public void editUserRecord() {
        String username = authenticate();
        if(!(username == null)){
            while(true) {
                System.out.println("Please enter your new password:");
                String password = getInput();
                if (checkPassword(password)) {
                    System.out.println("Please re-enter your new password:");
                    String re_password = getInput();
                    if (checkRePassword(password, re_password)) {
                        System.out.println("Please enter your new full name:");
                        String fullname = getInput();
                        System.out.println("Please enter your new email address:");
                        String emailaddress = getInput();
                        String hashedPassword = hash(password);
                        for(User user : UserList){
                            if(user.checkUsername(username))
                                user.editRecord(hashedPassword, fullname, emailaddress);
                        }
                        System.out.println("Record update successfully!");
                    }
                    else{
                        System.out.println("New passwords do not match, user record not edited!");
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
}
