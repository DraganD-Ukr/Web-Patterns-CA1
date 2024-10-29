package Application;

import java.util.Scanner;
import java.util.regex.Pattern;
import Utils.Encryption;
import daos.UserDAO;
import daos.UserDAOimpl;
import Business.User;

public class MusicPlaylistApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDAO userDAO = new UserDAOimpl("CA1_test");
    private static final Encryption encryption = new Encryption();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Cya later Dude!");
                    break;
                default:
                    System.out.println("Wata heck are you doing dude?");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n (っ◔◡◔)っ Music App Menu (っ◔◡◔)っ");
        System.out.println("1 Register");
        System.out.println("2 Login");
        System.out.println("3 Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getMenuChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input please enter a number.");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }

    public static void register() {
        String firstName = validateInput("Enter first name: ", "^[A-Za-z]{2,30}$", "Invalid first name. Use 2-30 alphabetic characters.");
        String lastName = validateInput("Enter last name: ", "^[A-Za-z]{2,30}$", "Invalid last name. Use 2-30 alphabetic characters.");
        String username = validateUsername();
        String password = validatePassword(username);
        validateReenteredPassword(password);
        String creditCardNumber = validateInput("Enter credit card number: ", "^\\d{16}$", "Invalid credit card number enter 16 digits.");

        // Hash the password before storing
        String hashedPassword = encryption.hashPassword(password);

        // Create and store the new user
        User newUser = new User(firstName, lastName, username, hashedPassword, 0);
        if (userDAO.addUser(newUser)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed try again.");
        }
    }

    public static void login() {
        String username = validateInput("Enter username: ", "^[A-Za-z0-9_]{3,20}$", "Invalid username. Use 3-20 alphanumeric characters or underscores.");
        String password = validateInput("Enter password: ", ".*", "");

        if (authenticateUser(username, password)) {
            System.out.println("Login successful! Welcome, " + username + "!");
            // Here you can add code to navigate to the user's dashboard or main application area
        } else {
            System.out.println("Login failed invalid username or password.");
        }
    }

    private static boolean authenticateUser(String username, String password) {
        User user = userDAO.getUserByName(username);
        if (user != null) {
            return encryption.checkPasswordWithUsername(password, username);
        }
        return false;
    }

    private static String validateInput(String prompt, String regex, String errorMessage) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!Pattern.matches(regex, input)) {
                System.out.println(errorMessage);
            }
        } while (!Pattern.matches(regex, input));
        return input;
    }

    private static String validateUsername() {
        String username;
        do {
            username = validateInput("Enter username: ", "^[A-Za-z0-9_]{3,20}$", "Invalid username use 3-20 alphanumeric characters or underscores.");
            if (userDAO.existsbyUserName(username)) {
                System.out.println("Username already exists choose another one.");
            }
        } while (userDAO.existsbyUserName(username));
        return username;
    }

    private static String validatePassword(String username) {
        String password;
        do {
            password = validateInput("Enter password: ", "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
                    "Invalid password. It must contain at least 8 characters, including uppercase, lowercase, number, and special character.");
            if (password.toLowerCase().contains(username.toLowerCase())) {
                System.out.println("Password cannot contain the username.");
            }
        } while (password.toLowerCase().contains(username.toLowerCase()));
        return password;
    }

    private static void validateReenteredPassword(String password) {
        String reenteredPassword;
        do {
            reenteredPassword = validateInput("Re-enter password: ", ".*", "");
            if (!reenteredPassword.equals(password)) {
                System.out.println("Passwords do not match try again.");
            }
        } while (!reenteredPassword.equals(password));
    }
}
// create the menu first


// do functions one by one


    //use encryption from utils package for password hash


    //register                       //use regex
        //validate first name
        //validate last name
        //validate username
        //validate password based on username
        //re enter password
        //validate credit card number


    //login
        //validate username
        //validate password
        //check if username and password match
        //if match, login
        //if not, display error message


