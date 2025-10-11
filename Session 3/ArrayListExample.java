import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListExample {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        System.out.println("List1: " + list1);

        list1.remove(1);
        System.out.println("List1 after removing element at index 1: " + list1);

        // list1.set(2, 20); // This will throw IndexOutOfBoundsException

        ArrayList<ArrayList<Integer>> twoDList = new ArrayList<>();
        twoDList.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        twoDList.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        System.out.println("twoDList: " + twoDList);
    }
}
