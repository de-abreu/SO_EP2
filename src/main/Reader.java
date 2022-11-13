package main;

import java.io.FileNotFoundException;

public class Reader implements Runnable {
    private Database db;
    private Lock lock;
    private AccessControl controller;
    private boolean concurrency;

    public Reader(Database db, AccessControl controller, Lock lock, boolean concurrency)
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

    private void read() {
        for (int i = 0; i < 100; i++) {
            String s = db.data.get((int) Math.random() * db.data.size());
        }
        sleep();
    }

    private void runConcurrently() {
        controller.startReading();
        read();
        controller.stopReading();
    }

    private void runNonConcurrently() {
        try {
            lock.Activate();
            read();
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
