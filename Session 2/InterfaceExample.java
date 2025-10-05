import java.io.Serializable;

public class InterfaceExample {

    public static void main(String[] args) {
        ProgrammingLanguage pl = new ProgrammingLanguage();
        pl.getName("Java");
        System.out.println("Number of characters: " + pl.getNumberOfCharachters());
        System.out.println("Number of words: " + pl.getNumberOfWords());
        pl.display(); // Calling the default method from Language interface
        // p1.toString();

        InterfaceExample interfaceExample = new InterfaceExample();
        interfaceExample.toString();
        interfaceExample.equals(interfaceExample);
    }
}

interface Language {
    void getName(String name);
    int getNumberOfCharachters();
    int getNumberOfWords();
    int count = 0; // public, static and final by default

    default void display(){
        System.out.println("This is a default method in the Language interface.");
    }

    // int add(int a, int b); // public and abstract by default
}

interface Parser {
    void getName(String name);
    default void display(){
        System.out.println("This is a default method in the Parser interface.");
    }
}

class ProgrammingLanguage implements Language, Parser {

    @Override
    public void getName(String name) {
        System.out.println(name);
    }

    @Override
    public int getNumberOfCharachters() {
        return 232;
    }

    @Override
    public int getNumberOfWords() {
        return 1500;
    }

    @Override
    public void display() {
        Language.super.display();
        Parser.super.display();
        System.out.println("This is the overridden display method in ProgrammingLanguage class.");
    }

}
