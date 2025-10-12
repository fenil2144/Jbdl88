public final class FinalKeyword {

    public static void main(String[] args) {
        // final variable - constant value
        final int a = 10;
        // a = 20; // Compile time error: cannot assign a value to final variable a
        System.out.println("Value of final variable a: " + a);

        final int b;
        b = 20;
       //  b= 30;  // Compile time error: cannot assign a value to final variable b
        System.out.println("Value of final variable b: " + b);

        final FinalKeyword FINAL_KEYWORD = new FinalKeyword();
    }

    final void someMethod(){
        System.out.println("This is some method");
    }

}

// Not allowed to inherit a final class
//class AnotherClass1 extends FinalKeyword{
//
//}
