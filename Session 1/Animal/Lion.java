package Animal;

import AnimalPackage.CarnivorousAnimal;

public class Lion extends CarnivorousAnimal {

    public static void main(String[] args) {
        System.out.println("Lion class is being executed.");

        CarnivorousAnimal carnivorousAnimal = new CarnivorousAnimal();
        // carnivorousAnimal.species = "Panthera leo";  Not accessible (private)
        // carnivorousAnimal.habitat = "Savannah";  // Not Accessible (default)
        // carnivorousAnimal.age = 5;  Not Accessible (protected, same package)
        carnivorousAnimal.name = "Simba";  // Accessible (public)


        Lion lion = new Lion();
        lion.age = 6;  // Accessible (protected, subclass)
        lion.name = "Mufasa";  // Accessible (public)
        // lion.habitat = "Savannah";  // Not Accessible (default)
        // lion.species = "Panthera leo";  // Not Accessible (private)
    }
}
