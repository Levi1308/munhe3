package UI;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Manager manager = new Manager();
        manager.readFile(args[0]);
        manager.runGame();

    }
}
