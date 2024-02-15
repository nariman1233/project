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
          System.out.println("1.  create User");
          System.out.println("0.  Exit");
          System.out.println();
          try {
              System.out.println("Enter option (1-3): ");
              int option = scanner.nextInt();
              if(option == 1){
                  createUserMenu();
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

        String response = controller.createUser(name,  surname , gender);
        System.out.println(response);

    }
}
