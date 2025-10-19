import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class PrimitiveStreamExample {

    public static void main(String[] args) {

        int[] integerArray = {1, 2, 3, 5, 6, 7, 8, 9};

        IntStream intStream = Arrays.stream(integerArray);
        System.out.println("Sum of elements in integerArray: " + intStream.sum());
        System.out.println("Max of elements in integerArray: " + intStream.max());

        // Primitive Stream to List
        List<Integer> integerList = IntStream.of(2,4,6,8).boxed()
                .collect(Collectors.toList());
        System.out.println("Integer List from Primitive Stream: " + integerList);

        DoubleStream.of(1.0, 2.5, 3.8, 4.6)
                .forEach(d -> System.out.println("DoubleStream element: " + d));

        // Primitive Streams to Array
        IntStream.of(2,5,6,8).toArray();

        // LongStream

    }
}
