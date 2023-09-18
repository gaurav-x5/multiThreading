import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueWithMutex<T> {
    private T[] array;
    private int size;
    private int capacity;
    private int head;
    private int tail;
    Lock lock = new ReentrantLock();

    public BlockingQueueWithMutex(int capacity) {
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public void enqueue(T obj) {

        lock.lock();
        while(size == capacity) {
            lock.unlock();
            lock.lock();
        }

        if(tail == capacity) {
            tail = 0;
        }

        array[tail] = obj;
        tail++;
        size++;
        lock.unlock();
    }

    public T dequeue() {

        lock.lock();
        while(size == 0) {
            lock.unlock();
            lock.lock();
        }

        if(head == capacity) {
            head = 0;
        }

        T val = array[head];
        array[head] = null;
        head++;
        size--;

        lock.unlock();
        return val;
    }


}
