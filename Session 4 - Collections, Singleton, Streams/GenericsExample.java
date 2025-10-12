import java.util.ArrayList;
import java.util.List;

class Test<T> {
    T object;
    Test(T object){     // Constructor
        this.object = object;
    }
    public T getObject(){
        return this.object;
    }
}
public class GenericsExample {

    public static void main(String[] args) {

        // Instance of Integer Type
        Test<Integer> integerTest = new Test<>(15);
        System.out.println(integerTest.getObject());

        // Instance of String Type
        Test<String> stringTest = new Test<>("Hello World!");
        // stringTest.object = 10;
        System.out.println(stringTest.getObject());

        List<Object> listObject = new ArrayList<>();
        listObject.add(100);
        listObject.add("Generics in Java");
        listObject.add(200.00);

        // integerTest = stringTest;
    }
}
