public class Main {
    public static void main(String[] args) throws InterruptedException {

//        BlockingQueue<Integer> bq = new BlockingQueue<>(5);
//        BlockingQueueWithMutex<Integer> bq = new BlockingQueueWithMutex<>(5);

//        Thread t1 = new Thread(()-> {
//            for(int i = 0; i < 50; i++) {
//                try {
//                    bq.enqueue(i);
//                    System.out.println("enqued value => "+ i);
//                }catch (InterruptedException ex) {
//
//                }
//            }
//        });
//
//        Thread t2 = new Thread(()-> {
//
//            for(int i = 0; i < 25; i++) {
//                try{
//                    int val = bq.dequeue();
//                    System.out.println("dequeued2 value is => "+ val);
//                } catch (InterruptedException ex) {
//
//                }
//            }
//        });
//
//        Thread t3 = new Thread(()-> {
//
//            for(int i = 0; i < 25; i++) {
//                try{
//                    int val = bq.dequeue();
//                    System.out.println("dequeued3 value is => "+ val);
//                } catch (InterruptedException ex) {
//
//                }
//            }
//        });
//
//        t1.start();
//        Thread.sleep(4000);
//        t2.start();
//
//        t2.join();
//        t3.start();
//        t1.join();
//        t3.join();

//        Thread producer1 = new Thread(()->{
//            int i = 1;
//            while (true) {
//                bq.enqueue(i);
//                System.out.println("Producer thread 1 enqueued " + i);
//                i++;
//            }
//        });
//
//        Thread producer2 = new Thread(()-> {
//            int i = 5000;
//            while (true) {
//                bq.enqueue(i);
//                System.out.println("Producer thread 2 enqueued " + i);
//                i++;
//            }
//        });
//
//        Thread producer3 = new Thread(()-> {
//            int i = 5000;
//            while (true) {
//                bq.enqueue(i);
//                System.out.println("Producer thread 2 enqueued " + i);
//                i++;
//            }
//        });
//
//        Thread consumer1 = new Thread(()->{
//            while (true) {
//                System.out.println("Consumer thread 1 dequeued " + bq.dequeue());
//            }
//        });
//
//        Thread consumer2 = new Thread(()->{
//            while (true) {
//                System.out.println("Consumer thread 2 dequeued " + bq.dequeue());
//            }
//        });
//
//        Thread consumer3 = new Thread(()->{
//            while (true) {
//                System.out.println("Consumer thread 3 dequeued " + bq.dequeue());
//            }
//        });
//
//        producer1.setDaemon(true);
//        producer2.setDaemon(true);
//        producer3.setDaemon(true);
//        consumer1.setDaemon(true);
//        consumer2.setDaemon(true);
//        consumer3.setDaemon(true);
//
//        producer1.start();
//        producer2.start();
//        producer3.start();
//
//        consumer1.start();
//        consumer2.start();
//        consumer3.start();
//
//        Thread.sleep(1000);

        final BlockingQueueWithSemaphore<Integer> q = new BlockingQueueWithSemaphore<Integer>(5);

        Thread t1 = new Thread(new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 20; i++) {
                        q.enqueue(new Integer(i));
                        System.out.println("enqueued " + i);
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Thread 2 dequeued: " + q.dequeue());
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread t3 = new Thread(new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Thread 3 dequeued: " + q.dequeue());
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        t1.start();
        Thread.sleep(4000);
        t2.start();
        t2.join();

        t3.start();
        t1.join();
        t3.join();

    }
}