public class MethodOverloading {

    public double sum(double a, double b) {
        return (a + b);
    }

    public int sum(int a, int b, int c) {
        return (a + b + c);
    }

    public static void main(String[] args) {
        MethodOverloading obj = new MethodOverloading();
        System.out.println(obj.sum(5.5, 4.5));
        System.out.println(obj.sum(10, 20, 30));
        System.out.println(obj.sum(10, 20));

        // System.out.println(obj.sum(10.1,2.1,30.1)); // This will give error as there is no method with this signature
    }
}
