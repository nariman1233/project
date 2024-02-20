import controllers.UserController;
import entities.User;
import repositories.interfaces.IUserRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final UserController controller;
    private final Scanner scanner;
    private final IUserRepository repo;

    public MyApplication(UserController controller , IUserRepository repo){
        this.controller = controller;
        this.repo = repo;
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
        int balance = scanner.nextInt();

        User createdUser = controller.createUser(name, surname, gender, password, balance);
        if (createdUser != null) {
            System.out.println("User was created with ID: " + createdUser.getId());
        } else {
            System.out.println("User creation was failed!");
        }

    }
    public void getUserByIdAndPasswordMenu(){
        System.out.println("Please enter your id");
        int id = scanner.nextInt();
        System.out.println("Please enter your password:");
        String password = scanner.next();


        String response = controller.getUser(id , password);
        if (response.equals("User was not found!")) {
            System.out.println(response);
            return;
        }


        User user = repo.getUser(id , password);
        if (user != null) {
            String userPassword = user.getPassword();
            if (userPassword != null && userPassword.equalsIgnoreCase(password)) {
                System.out.println("Hello " + user.getName());
                System.out.println("Welcome to your account!");
                System.out.println("Your balance: " + user.getBalance());
                while (true) {
                    System.out.println();
                    System.out.println("User Menu:");
                    System.out.println("1. replenish ");
                    System.out.println("2. Withdraw ");
                    System.out.println("3. deposit");
                    System.out.println("0. Exit");

                    try {
                        System.out.print("Select option: ");
                        int option = scanner.nextInt();
                        switch (option) {
                            case 1:
                                deposit(user);
                                break;
                            case 2:
                                withdraw(user);
                                break;
                            case 3:
                                depositWithInterestMenu();
                            case 0:
                                System.out.println("Exiting user menu...");
                                return;
                            default:
                                System.out.println("Invalid option. Please choose from the menu.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Input must be an integer.");
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            } else {
                System.out.println("Invalid password. Access denied.");
            }
        } else {
            System.out.println("User not found!");
        }
    }

    private void deposit(User user) {
        try {
            System.out.print("Enter amount to deposit: ");
            int amount = scanner.nextInt();
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be positive.");
            }
            user.setBalance(user.getBalance() + amount);
            if (repo.updateUser(user)) {
                System.out.println("Balance updated successfully.");
            } else {
                System.out.println("Failed to update balance.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Input must be an integer.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void withdraw(User user) {
        try {
            System.out.print("Enter the amount to withdraw: ");
            int amount = scanner.nextInt();
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero.");
            }
            if (amount > user.getBalance()) {
                throw new IllegalArgumentException("Insufficient funds.");
            }
            user.setBalance(user.getBalance() - amount);
            if (repo.updateUser(user)) {
                System.out.println("Withdrawal successful. Updated balance: " + user.getBalance());
            } else {
                System.out.println("Failed to update balance.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Input must be an integer.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void depositWithInterestMenu() {
        System.out.println("Please enter your user ID:");
        int userId = scanner.nextInt();
        System.out.println("Please enter the amount you want to deposit:");
        double amount = scanner.nextDouble();
        System.out.println("Please enter the deposit term (in years):");
        int years = scanner.nextInt();

        String response = controller.getUser2(userId);
        if (response.equals("User was not found!")) {
            System.out.println(response);
            return;
        }

        User user = repo.getUser2(userId);
        if (user != null) {
            double finalAmount = calculateDepositWithInterest(amount, years);
            System.out.println("Initial amount: " + amount);
            System.out.println("Annual interest rate: 15%");
            System.out.println("Deposit term: " + years + " years");
            System.out.println("Final amount after " + years + " years: " + finalAmount);
        } else {
            System.out.println("User was not found!");
        }
    }

    private double calculateDepositWithInterest(double amount, int years) {
        double annualInterestRate = 0.15; // 15%
        double interest = amount * annualInterestRate * years;
        return amount + interest;
    }
}
