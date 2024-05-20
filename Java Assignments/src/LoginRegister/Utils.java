/*
Name: Landen Ingerslev
Assignment: LoginRegister
Description: Asks user for login, register, update or exit
Login checks input and gives a hello message if input is found in file and correct
register adds information to the text file with the new user and their data
update changes some information in the text file, having problems deleting the original file
 */

package LoginRegister;

import java.io.*;
import java.util.Scanner;

public class Utils {
    private static final String FILENAME = "src\\LoginRegister\\UserData.txt";
    private static final String DELIMITER = ":";
    public static void CreateUser(User user) throws IOException {
        File userData = new File(FILENAME);
        if(!userData.exists()) {
            System.out.println("File Not Found, Cannot Create User");
            System.exit(0);
        }

        FileWriter fileWriter = new FileWriter(userData, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(user.getName() + ":" + user.getPassword());

        printWriter.close();
        fileWriter.close();
    }
    public static User FetchUser(String username, String password) throws FileNotFoundException {
        File userData = new File(FILENAME);
        if(!userData.exists()) {
            System.out.println("File Not Found, Cannot Fetch User");
            System.exit(0);
        }

        Scanner fileReader = new Scanner(userData);
        while(fileReader.hasNextLine()) {
            String[] splitLine = fileReader.nextLine().split(DELIMITER);
            if(splitLine[0].equals(username) && splitLine[1].equals(password)) {
                return new User(username, password);
            }
        }
        fileReader.close();
        return null;
    }
    public static void UpdateUser(User user) throws IOException {
        File userData = new File(FILENAME);
        if(!userData.exists()) {
            System.out.println("File Not Found, Cannot Update User");
            System.exit(0);
        }

        String tempFileName = "src\\LoginRegister\\tempFile.txt";
        File tempFile = new File (tempFileName);

        FileWriter tempFileWriter = new FileWriter(tempFileName, true);
        PrintWriter tempPrintWriter = new PrintWriter(tempFileWriter);

        String oldUsername = user.getName();
        String oldPassword = user.getPassword();
        String newUsername, newPassword;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("New Username:");
            newUsername = input.nextLine();
            System.out.println("New Password:");
            newPassword = input.nextLine();
            if (oldPassword.equals(newPassword) || oldUsername.equals(newUsername))
                System.out.println("New username or password cannot be the same as the old username or password");
            else {
                user.setPassword(newUsername);
                user.setPassword(newPassword);
            }
        } while(oldPassword.equals(newPassword) && oldUsername.equals(newUsername));
        input.close();

        Scanner inputFile = new Scanner(userData);
        while(inputFile.hasNextLine()) {
            String[] splitLine = inputFile.nextLine().split(DELIMITER);
            if(splitLine[0].equals(oldUsername) && splitLine[1].equals(oldPassword))
                tempPrintWriter.println(user.getName() + DELIMITER + user.getPassword());
            else
                tempPrintWriter.println(splitLine[0] + DELIMITER + splitLine[1]);
        }
        inputFile.close();

        tempPrintWriter.close();
        tempFileWriter.close();

        userData.delete();
        File dump = new File(FILENAME);
        tempFile.renameTo(dump);
    }
}