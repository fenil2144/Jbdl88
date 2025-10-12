import java.io.IOException;

class ParentC {
    void msg(){
        System.out.println("Parent Class Method");
    }
}

public class TestExceptionChild extends ParentC {
    @Override
    void msg() throws ArithmeticException {
        System.out.println("Child class method");
    }

    public static void main(String[] args) {
        ParentC p = new TestExceptionChild();
        p.msg();
    }
}
