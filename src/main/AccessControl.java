package main;

public class AccessControl {
    private boolean writer = false;
    private int readers = 0;

    private boolean canRead() {
        return !writer;
    }

    private boolean canWrite() {
        return readers == 0 && canRead();
    }

    public void blocked() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void startReading() {
        while (!canRead())
            blocked();
        readers++;
    }

    synchronized void stopReading() {
        readers--;
        notifyAll();
    }

    public synchronized void startWriting() {
        while (!canWrite())
            blocked();
        writer = true;
    }

    public synchronized void stopWriting() {
        writer = false;
        notifyAll();
    }
}
