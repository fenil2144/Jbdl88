public class WaitNotifyExample {

    int counter = 1;
    static int N;

    public void printOddNumber() {
        synchronized (this) {
            while (counter < N) {
                while (counter % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // Print odd number
                System.out.println(counter + " ");

                // Increment counter
                counter++;

                // Notify even thread
                notify();
            }
        }
    }

    public void printEvenNumber() {
        synchronized (this) {
            while (counter < N) {
                while (counter % 2 == 1) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // Print even number
                System.out.println(counter + " ");

                // Increment counter
                counter++;

                // Notify odd thread
                notify();
            }
        }
    }

    public static void main(String[] args) {
        N = 10;

        WaitNotifyExample waitNotifyExampleObj = new WaitNotifyExample();
        Thread t1 = new Thread(waitNotifyExampleObj::printEvenNumber);
        Thread t2 = new Thread(waitNotifyExampleObj::printOddNumber);

        // Start both threads
        t1.start();
        t2.start();
    }
}
