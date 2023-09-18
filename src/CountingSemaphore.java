public class CountingSemaphore {

    int usedPermits = 0;
    int maxCount;

    public CountingSemaphore(int count) {
        this.maxCount = count;
    }

    public CountingSemaphore(int count, int initialPermits) {
        this.maxCount = count;
        this.usedPermits = this.maxCount - initialPermits;
    }

    public synchronized void acquire() throws InterruptedException {

        if (usedPermits == maxCount)
            wait();

        notifyAll();
        usedPermits++;
    }

    public synchronized void release() throws InterruptedException {

        if (usedPermits == 0)
            wait();

        usedPermits--;
        notifyAll();
    }
}
