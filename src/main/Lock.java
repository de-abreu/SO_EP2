package main;

public class Lock {
    private boolean isLocked;
    private Thread hasLocked;
    private int countBlocked;

    public syncronized void Activate() {
        Thread current = Thread.currentThread();
        while (isLocked && hasLocked != current)
            wait();
        isLocked = true;
        hasLocked = current;
        countBlocked++;
    }

    public syncronized void Deactivate() {
        if(Thread.currentThread() != hasLocked)
            return;
        countBlocked--;
        if (countBlocked > 0)
            return;
        isLocked = false;
        notify();
    }
}
