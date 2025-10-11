import java.util.Scanner;

public class TryWithResourceExample implements AutoCloseable{
    public static void main(String[] args) {
        funcTryWithResource();
    }

    public static void funcTryWithResource() {
        try(Scanner scanner = new Scanner(System.in)){
            int number = scanner.nextInt();
            System.out.println("Number entered is: " + number);
        } catch (Exception exception){
            System.out.println("Exception occurred: " + exception.getMessage());
        }

        try(TryWithResourceExample obj = new TryWithResourceExample()){
            // business logic
        }
    }

    @Override
    public void close() {
        System.out.println("I am responsible for closing the resources");
    }
}
