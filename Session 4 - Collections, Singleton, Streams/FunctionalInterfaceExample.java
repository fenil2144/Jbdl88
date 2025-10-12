@FunctionalInterface
interface DoSumInterface {
    int getSum(int a, int b);

    default void display() {
        System.out.println("This is a default method in a functional interface.");
    }

    static void show() {
        System.out.println("This is a static method in a functional interface.");
    }
}

public class FunctionalInterfaceExample {

    public static void main(String[] args) {

        DoSumInterface doSum = new DoSumInterface() {
            @Override
            public int getSum(int a, int b) {
                return a + b;
            }
        };

        DoSumInterface doSum2 = new DoSumInterface() {
            @Override
            public int getSum(int a, int b) {
                return a + b + 10;
            }
        };
        System.out.println("Sum is: " + doSum.getSum(10, 20));
        doSum.display();
        DoSumInterface.show();

        // (Argument list) -> {Body}
        DoSumInterface lambdaSum = (a, b) -> {
            return (a + b);
        };
        System.out.println("Sum using lambda is: " + lambdaSum.getSum(15, 25));
    }

}
