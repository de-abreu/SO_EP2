package main;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import main.RandomSequence;

public class Database {
    private ArrayList<String> backup;
    public ArrayList<String> data;
    private Scanner scan;

    public Database(File file) throws FileNotFoundException {
        backup = new ArrayList<>();
        scan = new Scanner(file);
        while (scan.hasNext()) {
            backup.add(sc.nextLine());
        }
        data = backup;
    }
}
