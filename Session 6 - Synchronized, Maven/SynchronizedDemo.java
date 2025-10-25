class Sender {

    public synchronized void send(String message) {

        synchronized (this) {
            System.out.println(Thread.currentThread() + " sending message: " + message +
                    " at time " + System.currentTimeMillis());
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread() + message + " sent");
    }
}

class ThreadSend extends Thread {
    private String message;
    Sender sender;

    ThreadSend(String message, Sender sender) {
        this.message = message;
        this.sender = sender;
    }

    @Override
    public void run() {
        sender.send(message);
    }
}


public class SynchronizedDemo {

    public static void main(String[] args) {

        Sender sender = new Sender();
        ThreadSend thread1 = new ThreadSend("Hello!", sender);
        ThreadSend thread2 = new ThreadSend("Hi!", sender);

        thread1.start();
        thread2.start();
        //Thread[Thread-0,5,main] sending message: Hello! at time 1761386359449
        //Thread[Thread-1,5,main] sending message: Hi! at time 1761386359449

        // Synchronized output:
        //Thread[Thread-0,5,main] sending message: Hello! at time 1761386409807
        //Thread[Thread-0,5,main]Hello! sent
        //Thread[Thread-1,5,main] sending message: Hi! at time 1761386410009
        //Thread[Thread-1,5,main]Hi! sent
    }
}
