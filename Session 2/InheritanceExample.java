class Animal {

    String name;
    int age;

    void eat() {
        System.out.println(name + " is eating.");
    }
    void run() {
        System.out.println(name + " is running.");
    }
}

class Cat extends Animal {
    void meow() {
        System.out.println(name + " says Meow!");
    }
}

class Horse extends Animal {
    void neigh() {
        System.out.println(name + " says Neigh!");
    }
}

public class InheritanceExample {

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.name = "Whiskers";
        cat.age = 2;
        cat.eat();
        cat.run();
        cat.meow();

        Horse horse = new Horse();
        horse.name = "Star";
        horse.age = 5;
        horse.eat();
        horse.run();
        horse.neigh();
    }
}
