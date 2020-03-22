import java.io.*;

public class Main {
    public static void main(String[] args){
        AuthenticationSystem authenticationSystem = new AuthenticationSystem();
        int option = -1;
        while (option != 0){
            System.out.println("Welcome to the COMP2396 Authentication system!\n" +
                    "1. Authenticate user\n" +
                    "2. Add user record\n" +
                    "3. Edit user record\n" +
                    "What would you like to perform?\n" +
                    "Please enter your command (1-3, or 0 to terminate the system):");
            option = Integer.parseInt(authenticationSystem.getInput());
            if(option == 1){
                System.out.println("The input is: " + option);
                authenticationSystem.authenticate();
            }
            else if(option == 2){
                System.out.println("The input is: " + option);
                authenticationSystem.addUserRecord();
            }
            else if(option == 3){
                System.out.println("The input is: " + option);
                authenticationSystem.editUserRecord();
            }
            else{
                System.out.println("Invalid input, the command should be 1-3, or 0");
            }
        }
    }
}
