import java.io.IOException;

class ParentC {
    void msg() throws IOException{
        System.out.println("Parent Class Method");
    }
}

public class TestExceptionChild extends ParentC {
    @Override
    void msg() throws Exception {
        System.out.println("Child class method");
    }

    public static void main(String[] args) {
        ParentC p = new TestExceptionChild();
        p.msg();
    }
}
