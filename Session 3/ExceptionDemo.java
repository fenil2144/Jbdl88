public class ExceptionDemo {

    public static void main(String[] args) {
        int k = 1;
        System.out.println("Starting the program execution..");
        try{
            k = 0; // read from user input
            computeDivision(k);
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException exception) {
            System.out.println("Oops! Encountered an arithmetic exception for k = " + k);
        } catch (NullPointerException nullPointerException){
            System.out.println("Oops! Encountered a null pointer exception.");
        } catch(RuntimeException runtimeException){
            System.out.println("Oops! Encountered a runtime exception.");
        } catch(Exception exception){
            System.out.println("Oops! Encountered an exception.");
        }
        finally {
            System.out.println("Inside Finally block.");
        }
        System.out.println("Ending the program execution..");
    }

    private static void computeDivision(int i) {
        int x = 100 / i;
        System.out.println("Value of x: " + x);
    }
}
