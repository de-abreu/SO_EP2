package main;

import java.io.FileNotFoundException;

public class Writer implements Runnable {
    private Database db;
    private Lock lock;
    private AccessControl controller;
    private boolean concurrency;

    public Writer(Database db, AccessControl controller, Lock lock, boolean concurrency)
            throws FileNotFoundException {
        this.db = db;
        this.lock = lock;
        this.controller = controller;
        this.concurrency = concurrency;
    }

    private void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void write() {
        for (int i = 0; i < 100; i++) {
            db.data.set((int) Math.random() * db.data.size(), "MODIFICADO");
        }
        sleep();
    }

    private void runConcurrently() {
        controller.startWriting();
        write();
        controller.stopWriting();
    }

    private void runNonConcurrently() {
        try {
            lock.Activate();
            write();
            lock.Deactivate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (concurrency)
            runConcurrently();
        else
            runNonConcurrently();
    }
}
