package tictactoe;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.IOException;

public class SaveToFile {
    public static void save(Saveable toSave, String filename, String relativePath) {
        Path path = FileSystems.getDefault().getPath(relativePath, filename);
        try {
            Files.writeString(path, toSave.getStringToSave());
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
