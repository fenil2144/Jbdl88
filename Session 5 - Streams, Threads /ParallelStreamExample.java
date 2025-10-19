import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParallelStreamExample {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9, 11, 13, 12, 14, 18, 22, 45, 65);

        //numbers.stream - Sequential Stream
        numbers.parallelStream()
                .filter(integer -> {
                    System.out.println("In filter method: " + integer + " Thread: " + Thread.currentThread().getName());
                    return integer % 2 != 0;
                })
                .map(integer -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("In map method: " + integer + " Thread: " + Thread.currentThread().getName());
                    return integer * integer;
                })
                .forEachOrdered(x -> {
                    System.out.println("In forEachOrdered method: " + x + " Thread: " + Thread.currentThread().getName());
                });

        List<String> stringList = Arrays.asList("J","A","V","A","S","T","R","E","A","M");
        List<String> stringSet =  stringList.stream().collect(Collectors.toList());
        System.out.println(stringSet);
        stringList.parallelStream().forEach(System.out::print);

        //First Iteration output
        // [J, A, V, A, S, T, R, E, A, M]
        //RTEAVAJSAM

        //Second Iteration output
        // [J, A, V, A, S, T, R, E, A, M]
        // RTAEVAJSAM

        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
    }
}
