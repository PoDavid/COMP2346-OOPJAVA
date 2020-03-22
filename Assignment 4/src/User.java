public class User{
    private String userName;
    private String hashedPassword;
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private Integer failedLoginCount;
    private Boolean locked;

    User(String username, String hashedPassword, String fullname, String emailaddress, String phonenumber){
        this.userName=username;
        this.hashedPassword = hashedPassword;
        this.fullName = fullname;
        this.emailAddress = emailaddress;
        this.phoneNumber = phonenumber;
        this.failedLoginCount = 0;
        this.locked = false;
    }
    public Boolean checkUsername(String username){
        return userName.equals(username);
    }
    public void checkPassword(String password){
        if(locked){
            System.out.println("Login failed! Your account has been locked!");
        }
        else if(hashedPassword.equals(password)){
            System.out.println("Login success! Hello " + userName +"!");
            failedLoginCount=0;
        }
        else{
            System.out.println("Login failed!");
            failedLoginCount+=1;
            if(failedLoginCount>=3){
                locked=true;
            }
        }
    }
    public void editRecord(String hashedPassword, String fullname, String emailaddress){
        this.hashedPassword = hashedPassword;
        this.fullName = fullname;
        this.emailAddress = emailaddress;
    }
}
