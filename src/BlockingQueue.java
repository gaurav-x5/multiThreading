public class BlockingQueue<T> {
    T[] array;
    int capacity;
    int size;
    int head;
    int tail;

    public BlockingQueue(int capacity) {
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public synchronized void enqueue(T obj) throws InterruptedException {
        if(size == capacity) {
            wait();
        }

        if(tail ==  capacity) {
            tail = 0;
        }

        array[tail] = obj;
        tail++;
        size++;

        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        T item = null;

        if(size == 0) {
            wait();
        }

        if(head ==  capacity) {
            head = 0;
        }

        item = array[head];
        array[head] = null;
        head++;
        size--;

        notifyAll();
        return item;
    }
}
