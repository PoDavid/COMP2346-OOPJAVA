public class Main {
    public static void main(String[] args){
        AuthenticationSystem authenticationSystem = new AuthenticationSystem();
        int option;
        System.out.println("Welcome to the COMP2396 Authentication System!\n" +
                    "1. Authenticate user\n" +
                    "2. Add user record\n" +
                    "3. Edit user record\n" +
                    "What would you like to perform?");
        while (true){
            System.out.println("Please enter your command (1-3, or 0 to terminate the system):");
            option = Integer.parseInt(authenticationSystem.getInput());
            if(option == 0){
                break;
            }
            else if(option == 1){
                authenticationSystem.authenticate();
            }
            else if(option == 2){
                authenticationSystem.addUserRecord();
            }
            else if(option == 3){
                authenticationSystem.editUserRecord();
            }
            else{
                System.out.println("Invalid input, the command should be 1-3, or 0");
            }
        }
    }
}
