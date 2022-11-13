package main;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import main.RandomSequence;

enum Access {
    READ, WRITE
}

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
        init();
    }

    public void init() throws FileNotFoundException {
        data = backup;
    }

    public void RandomAccess(Access type) {
        if (type = Access.WRITE) {
            for (int i = 0; i < 100; i++) {
                String s = data.get((int) Math.random() * data.size());
            }
        } else {
            for (int i = 0; i < 100; i++) {
                data.set((int) Math.random() * data.size(), "MODIFICADO");
            }
        }
    }

}
