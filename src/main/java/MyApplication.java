import controllers.UserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final UserController controller;
    private final Scanner scanner;

    public MyApplication(UserController controller){
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void start() {
      while (true){
          System.out.println();
          System.out.println("Welcome to Bank managment system");
          System.out.println("Select option:");
          System.out.println("1.  create account");
          System.out.println("2.  authorization");
          System.out.println("0.  Exit");
          System.out.println();
          try {
              System.out.println("Enter option (1-3): ");
              int option = scanner.nextInt();
              if(option == 1){
                  createUserMenu();
              }
              else if(option == 2){
                  getUserByIdAndPasswordMenu();
              }
              else{
                  break;
              }
          }catch (InputMismatchException e) {
              System.out.println("Input must be an integer");
              scanner.nextLine();
          }catch (Exception e) {
              System.out.println(e.getMessage());
          }
          System.out.println("*****************************");
      }


    }
    public void createUserMenu(){
        System.out.println("Please Enter name");
        String name  = scanner.next();
        System.out.println("Please Enter surname");
        String surname = scanner.next();
        System.out.println("Please enter gender(male or female)");
        String gender = scanner.next();
        System.out.println("Please create a password");
        String password = scanner.next();
        System.out.println("To create an account you should make your first deposit");
        int balance = Integer.parseInt(scanner.next());

        String response = controller.createUser(name,  surname , gender, password , balance);
        System.out.println(response);

    }
    public void  getUserByIdAndPasswordMenu(){
        System.out.println("Please enter your id");
        int id = scanner.nextInt();
        System.out.println("enter your password");
        String password = scanner.next();
        String response = controller.getUser(id , password);
        System.out.println(response);
    }
}
