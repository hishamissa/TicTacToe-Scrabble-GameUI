package tictactoe;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.IOException;

public class SaveToFileNumTTT {
    public static void save(Saveable toSave, String filename, String relativePath) {
        Path path = FileSystems.getDefault().getPath(relativePath, filename);
        try {
            Files.writeString(path, toSave.getStringToSave());
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static String load(Saveable toSave, String filename, String relativePath) {
        Path path = FileSystems.getDefault().getPath(relativePath, filename);
        String fileLines = "";
        try {
            fileLines = String.join("\n", Files.readAllLines(path));
            toSave.loadSavedString(fileLines); //change method call
            return fileLines;
        } catch (IOException e) {
            System.out.println("Error reading");
        }
    return fileLines;        
    }
}
