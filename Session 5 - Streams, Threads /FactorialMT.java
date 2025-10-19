import java.math.BigInteger;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FactorialMT extends Thread {

    int number;
    BigInteger result;

    public FactorialMT(int number) {
        this.number = number;
    }

    public static void main(String[] args) {
        Integer[] numbers = {10000, 20000, 50000, 30000, 43000, 50000, 65000, 15000, 42000};
        long start = System.currentTimeMillis();

        List<FactorialMT> threads = Arrays.stream(numbers)
                .map(number -> {
                    FactorialMT thread = new FactorialMT(number);
                    thread.start();
                    return thread;
                }).collect(Collectors.toList());

        threads.stream().forEach(x -> {
            try {
                x.join();
                System.out.println("Input = " + x.number + ", Factorial = " + x.result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        long end = System.currentTimeMillis();
        System.out.println("Time taken for large numbers with multithreading: " + (end - start) / 1000.0 + " seconds");
        // Time taken for large numbers with multithreading: 2.089 seconds
    }

    @Override
    public void run() {
        this.result = Factorial.calculateFactorial(this.number);
    }
}
