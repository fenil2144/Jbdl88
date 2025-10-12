import com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXEventWriter;

public class Dog {

    static {

        System.out.println("Dog class is being loaded.");
    }

    public Dog() {
        System.out.println("A new dog has been created!");
    }

    public Dog(String name) {
        this.name = name + " the Dog";
        System.out.println("A new dog named " + name + " has been created!");
    }

    public Dog(int age) {
        this.age = age;
    }

    public Dog(String name, int age, String color) {
        this(age); // constructor chaining
        this.age = age;
        this.color = color;
        System.out.println("A new dog named " + name + " has been created!");
    }

    // identity
    public String name;

    // state
    public int age;
    public static String breed;
    public String color;

    // behavior
    public void bark() {
        System.out.println("Woof! Woof!");
        System.out.println("Breed is " + breed);
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.name = "Tom";
        myDog.age = 3;
        myDog.breed = "Golden Retriever";
        myDog.color = "Golden";

        myDog.bark();
        myDog.eat();
        myDog.sleep();

        Dog anotherDog = new Dog("Buddy", 2, "Brown and White");
        anotherDog.bark();
        anotherDog.eat();
        anotherDog.sleep();
    }
}
