package main;

public class Lock {
    private boolean isLocked = false;
    private Thread hasLocked = null;
    private int countBlocked = 0;

    public synchronized void Activate() throws InterruptedException {
        Thread current = Thread.currentThread();
        while (isLocked && hasLocked != current)
            wait();
        isLocked = true;
        hasLocked = current;
        countBlocked++;
    }

    public synchronized void Deactivate() {
        if (Thread.currentThread() != hasLocked)
            return;
        countBlocked--;
        if (countBlocked > 0)
            return;
        isLocked = false;
        notify();
    }
}
