/**
 * The User class, used to model an user record.
 * @author Po Yat Ching David UID:3035372098
 */
public class User{
    private String userName;
    private String hashedPassword;
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private Integer failedLoginCount;
    private Boolean locked;

    /**
     * Instantiates a new User.
     *
     * @param username       the username
     * @param hashedPassword the hashed password
     * @param fullname       the fullname
     * @param emailaddress   the emailaddress
     * @param phonenumber    the phonenumber
     */
    User(String username, String hashedPassword, String fullname, String emailaddress, String phonenumber){
        this.userName=username;
        this.hashedPassword = hashedPassword;
        this.fullName = fullname;
        this.emailAddress = emailaddress;
        this.phoneNumber = phonenumber;
        this.failedLoginCount = 0;
        this.locked = false;
    }

    /**
     * Check if the given username matches with the record's userName
     *
     * @param username the provided username
     * @return the boolean whether the given username matches the record
     */
    public Boolean checkUsername(String username){
        return userName.equals(username);
    }

    /**
     * If User record is locked, return false
     * Else Check if the given hashed password matches with the record's hatched password.
     * Then Update Record accordingly.
     *
     * @param password the given hashed password
     * @return the boolean whether the user passes password checking step
     */
    public boolean checkPassword(String password){
        if(locked){
            System.out.println("Login failed! Your account has been locked!");
        }
        else if(hashedPassword.equals(password)){
            System.out.println("Login success! Hello " + userName +"!");
            failedLoginCount=0;
            return true;
        }
        else{
            System.out.println("Login failed!");
            failedLoginCount+=1;
            if(failedLoginCount>=3){
                locked=true;
            }
        }
        return false;
    }

    /**
     * Perform the edit record option.
     * Update the record according to the given new hashed password, new full name, and new email address.
     *
     * @param hashedPassword the new hashed password
     * @param fullname       the new fullname
     * @param emailaddress   the new emailaddress
     */
    public void editRecord(String hashedPassword, String fullname, String emailaddress){
        this.hashedPassword = hashedPassword;
        this.fullName = fullname;
        this.emailAddress = emailaddress;
    }
}
