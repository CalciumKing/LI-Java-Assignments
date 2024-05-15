/*
Name: Landen Ingerslev
Description: asks user for a name and a home for that person
the code goes through all names in the original file, if the
name matches and the home doesnt, it creates a new file with the
new information, then deletes the original, then it renames the new
file to the original name, appearing to update the original file
with the new information.*/

package NewHomes;

import java.io.*;
import java.util.Scanner;

public class Utils {
    public static final String FILENAME = "src\\NewHomes\\lotrHomes.txt";
    public static final String DELIMITER = ":";
    public static void updateRecord(String name, String home) throws IOException {
        String tempFileName = "src\\NewHomes\\tempFile.txt";
        File original = new File(FILENAME);
        File tempFile = new File(tempFileName);

        FileWriter tempPrintWriter = new FileWriter(tempFile, true);
        PrintWriter ogPrintWriter = new PrintWriter(tempPrintWriter);

        Scanner inputFile = new Scanner(original);
        while(inputFile.hasNextLine()) {
            String[] splitLine = inputFile.nextLine().split(DELIMITER);
            if(splitLine[0].equalsIgnoreCase(name) && !splitLine[2].equalsIgnoreCase(home))
                ogPrintWriter.println(splitLine[0] + DELIMITER + splitLine[1] + DELIMITER + home + DELIMITER + splitLine[3]);
            else
                ogPrintWriter.println(splitLine[0] + DELIMITER + splitLine[1] + DELIMITER + splitLine[2] + DELIMITER + splitLine[3]);
        }
        inputFile.close();

        ogPrintWriter.close();
        tempPrintWriter.close();
        original.delete();
        File dump = new File(FILENAME);
        tempFile.renameTo(dump);
    }
}