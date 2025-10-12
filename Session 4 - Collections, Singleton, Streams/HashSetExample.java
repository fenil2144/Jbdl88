import java.util.HashSet;
import java.util.Set;

public class HashSetExample {

    public static void main(String[] args) {
        Set<Integer> evenNumbers = new HashSet<Integer>();
        evenNumbers.add(2);
        evenNumbers.add(4);

        Set<Integer> oddNumbers = new HashSet<>();
        oddNumbers.add(1);
        oddNumbers.add(3);

        // Intersection operation
        evenNumbers.retainAll(oddNumbers);
        System.out.println("Intersection: " + evenNumbers); // Should print an empty set
    }
}
