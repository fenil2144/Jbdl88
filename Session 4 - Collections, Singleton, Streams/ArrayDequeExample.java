import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeExample {

    public static void main(String[] args) {
        Deque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(1);
        arrayDeque.add(2);
        arrayDeque.addFirst(10);
        System.out.println("ArrayDeque: " + arrayDeque);
        arrayDeque.addLast(20);
        System.out.println("ArrayDeque: " + arrayDeque);
    }
}
