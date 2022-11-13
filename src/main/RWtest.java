package main;

import java.io.File;
import java.io.FileNotFoundException;

public class RWtest {
    private static Lock lock;
    private static AccessControl controller;
    private static Database db;
    private static int variety = 100, repeat = 50;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        lock = new Lock();
        controller = new AccessControl();

        try {
            db = new Database(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("File " + args[0] + "not found");
            return;
        }

        boolean[] types = { true, false };
        for (boolean concurrency : types) {
            System.out.println("Concurrency: " + concurrency);
            System.out.println("Total running time: " + totalTime(concurrency) + " min");
        }
    }

    private static long totalTime(boolean concurrency) throws FileNotFoundException, InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i <= variety; i++)
            System.out.println("Average running time for " + i + " writers and " + (100 - i) + " readers: "
                    + averageTime(i, concurrency) + " milliseconds");
        long end = System.currentTimeMillis();
        return (end - start) / 60000;
    }

    private static int averageTime(int proportion, boolean concurrency)
            throws FileNotFoundException, InterruptedException {
        int average = 0;
        for (int i = 0; i < repeat; i++) {
            db.setup();
            Thread[] threads = generateThreads(proportion, concurrency);
            long start = System.currentTimeMillis();
            startThreads(threads);
            joinThreads(threads);
            long end = System.currentTimeMillis();
            average += end - start;
        }
        return average / repeat;
    }

    private static Thread[] generateThreads(int proportion, boolean concurrency) throws FileNotFoundException {
        Thread[] threads = new Thread[100];
        int[] sequence = RandomSequence.generate(variety);
        int i = 0;
        while (i < proportion)
            threads[sequence[i++]] = new Thread(new Writer(db, controller, lock, concurrency));
        while (i < variety)
            threads[sequence[i++]] = new Thread(new Reader(db, controller, lock, concurrency));
        return threads;
    }

    private static void startThreads(Thread[] threads) {
        for (Thread thread : threads)
            thread.start();
    }

    private static void joinThreads(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads)
            thread.join();
    }
}
