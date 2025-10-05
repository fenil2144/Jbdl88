class Parent {
    int a = 10;

    public void displayHello() {
        System.out.println("Hello from parent!");
    }

    protected void method() {
        System.out.println("This is a method from parent class");
    }

    static class nestedClass {
        void display() {
            System.out.println("This is a nested static class in Parent");
        }
    }
}

class Child extends Parent {
    int a = 20;

    @Override
    protected void method(){
        super.method(); // Call the parent class method
        System.out.println("This is a method from child class");
    }
}

public class MethodOverriding {
    public static void main(String[] args) {
        // Parent reference to parent class object
        Parent parentObj = new Parent();
        parentObj.displayHello();
        parentObj.method();
        System.out.println("Value of a in parent class: " + parentObj.a);

        // Child reference to child class object
        Child childObj = new Child();
        childObj.displayHello();
        childObj.method();
        System.out.println("Value of a in child class: " + childObj.a);

        // Parent reference to child class object
        Parent parentRef = new Child();
        parentRef.displayHello();
        parentRef.method();
        System.out.println("Value of a using parent reference: " + parentRef.a);

        // Child reference to parent class object (This will cause a compile-time error)
        // Child childRef = new Parent()
    }
}
