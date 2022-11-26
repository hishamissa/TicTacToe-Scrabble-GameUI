package tictactoe;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.IOException;

/**
 * Class to Save and Load into numerical tic tac toe
 */
public class SaveToFileNumTTT {
    /**
    * Method to get the path of the file to save to and writes the already
    * parsed string to it by calling getStringToSave()
    * @param toSave  the game to save
    * @param filename the file to save too
    * @param relativePath the folder with the file
    */
    public static void save(Saveable toSave, String filename, String relativePath) {
        Path path = FileSystems.getDefault().getPath(relativePath, filename);
        try {
            Files.writeString(path, toSave.getStringToSave());
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    /**
    * Method to get the path of the file to load in and reads the already
    * parsed string to it by calling loadSavedString()
    * @param toSave  the game to save
    * @param filename the file to save too
    * @param relativePath the folder with the file
    * @return String  returns fileLines
    */
    public static String load(Saveable toSave, String filename, String relativePath) {
        Path path = FileSystems.getDefault().getPath(relativePath, filename);
        String fileLines = "";
        try {
            fileLines = String.join("\n", Files.readAllLines(path));
            toSave.loadSavedString(fileLines);
            return fileLines;
        } catch (IOException e) {
            System.out.println("Error reading");
        }
    return fileLines;        
    }
}
