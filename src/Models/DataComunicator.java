package Models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataComunicator {

    Path _path = Path.of("dane.json");

    public void SaveFile(String data) throws IOException {
        Files.writeString(_path, data);
    }

    public String ReadTasks()
    {
        try {
            return Files.readString(_path);
        } catch (IOException e) {
            System.err.println("Błąd odczytu: " + e.getMessage());
        }

        return "";
    }
}
