// Approach 1: Extending the Thread class
class ThreadA extends Thread {
    public void run() {
        System.out.println("Thread A: " + Thread.currentThread());
        for (int i = 20; i > 0; i--) {
            System.out.println("Thread A class with i=" + i);
        }
    }
}

class ThreadB extends Thread {
    public void run() {
        System.out.println("Thread B: " + Thread.currentThread());
        for (int j = 20; j > 0; j--) {
            System.out.println("Thread B class with j=" + j);
        }
    }
}

// Approach 2: Implementing the Runnable interface

class ThreadRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread Runnable: " + Thread.currentThread());
        for (int k = 20; k > 0; k--) {
            System.out.println("Thread Runnable class with k=" + k);
        }
    }
}


public class ThreadBasicExample {
    public static void main(String[] args) {
        System.out.println("Main thread " + Thread.currentThread());
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        threadA.start();
        try {
            threadA.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        threadB.start();

        ThreadRunnable threadRunnable = new ThreadRunnable();
        Thread threadC = new Thread(threadRunnable);
        threadC.start();

        // threadC.setDaemon(true); - Only before start method
        // Is it possible to call start and run method twice?
        // threadA.start(); - No, only once.
        // threadA.run(); // Yes you can call run method multiple times

        System.out.println("----- New Line ----");

    }
}
