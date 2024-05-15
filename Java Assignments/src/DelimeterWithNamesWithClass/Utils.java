package DelimeterWithNamesWithClass;

import java.io.*;
import java.util.Scanner;

public class Utils {
    public static final String DELIMITER = ":";
    public static final String FILENAME = "names.txt";
    public static void updateStudentNames(String oldName, String newName) throws IOException {
        String tempFileName = "temp.txt";
        File originalFile = new File(FILENAME);
        File tempFile = new File(tempFileName);

        FileWriter fileWriter = new FileWriter(tempFile, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        Scanner fileInput = new Scanner(originalFile);

        while(fileInput.hasNextLine()) {
            String[] line = fileInput.nextLine().split(DELIMITER);
            System.out.println(fileInput.nextLine());
            if(line[0].equalsIgnoreCase(oldName))
                printWriter.println(newName + DELIMITER + line[1]);
            else
                printWriter.println(line[0] + DELIMITER + line[1]);
        }

        fileInput.close();
        printWriter.flush();
        fileWriter.close();

        boolean fileDeleted = originalFile.delete();
        File dump = new File(FILENAME);
        boolean fileRenamed = tempFile.renameTo(dump);

        /*File newFile = new File(tempFileName);
        if(newFile.delete())
            tempFile.renameTo(originalFile);
        else
            System.out.println("Failed To Modify The File");*/
    }
}