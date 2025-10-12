import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 6, 7, 8);

        List<Integer> annonymousInnerClassList = numbers.stream().filter(
                new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        System.out.println("In Test method of annonymous inner class with integer: " + integer);
                        return integer % 2 == 0;
                    }
                }).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                System.out.println("In Apply method of annonymous inner class with integer: " + integer);
                return integer * integer;
            }
        }).collect(Collectors.toList());

        System.out.println("Annonymous Inner Class List: " + annonymousInnerClassList);

        // Using Lambda Expressions
        Set<Integer> integerSet=  numbers.stream().filter(i -> {
            System.out.println("In test method of lambda expression with integer: " + i);
            return i % 2 == 0;
        }).map(integer -> {
            System.out.println("In apply method of lambda expression with integer: " + integer);
            return integer * integer;
        }).collect(Collectors.toSet());
        System.out.println("Integer Set using Lambda Expressions: " + integerSet);

        // Requirement: Starts witt J, has at least length of 4, result in uppercase and sorted alphabetically
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Doe", "Dane");
        names.stream().filter(name -> name.startsWith("J") && name.length() >3)
                .map(name -> name.toUpperCase())
                .sorted()
                .forEach(name -> System.out.println(name));


    }
}
