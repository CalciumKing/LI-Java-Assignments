package NewHomes;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the characters name");
        String name = input.nextLine();
        System.out.println("Enter the characters home");
        String home = input.nextLine();

        Utils.updateRecord(name, home);
    }
}