package LoginRegister;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static User user = new User();
    public static void main(String[] args) throws IOException {
        System.out.println("Would You like to Login(1), Register(2), Update(3), or Exit(4)?");
        switch(input.nextInt()) {
            case 1 -> login();
            case 2 -> register();
            case 3 -> update();
            default -> System.out.println("That is not an acceptable answer, program ending");
        }
    }
    private static void login() throws FileNotFoundException {
        input.nextLine();
        String username;
        String password;
        User fetch;
        do {
            System.out.println("Login:");
            System.out.println("Enter Username:");
            username = input.nextLine();
            System.out.println("Enter Password:");
            password = input.nextLine();

            fetch = Utils.FetchUser(username, password);

            if (fetch != null && username.equals(fetch.getName()) && password.equals(fetch.getPassword())) {
                System.out.println("Hello " + fetch.getName());
                System.out.println(fetch);
            }
            else
                System.out.println("That is incorrect, please enter the correct information");
        } while(fetch == null || !username.equals(fetch.getName()) || !password.equals(fetch.getPassword()));
    }
    private static void register() throws IOException {
        input.nextLine();
        System.out.println("Register:");
        System.out.println("Create a Username:");
        String username = input.nextLine();
        System.out.println("Create a Password:");
        String password = input.nextLine();

        user = new User(username, password);
        Utils.CreateUser(user);
    }
    private static void update() throws IOException {
        input.nextLine();

        String oldUsername;
        String oldPassword;
        do {
            System.out.println("Username:");
            oldUsername = input.nextLine();
            System.out.println("Old Password:");
            oldPassword = input.nextLine();
            if (Utils.FetchUser(oldUsername, oldPassword) == null)
                System.out.println("That is not the correct username or password");
            else {
                user.setName(oldUsername);
                user.setPassword(oldPassword);
            }
        } while (Utils.FetchUser(oldUsername, oldPassword) == null);
        Utils.UpdateUser(user);
    }
}