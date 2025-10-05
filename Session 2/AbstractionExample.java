public class AbstractionExample {
}

abstract class Bank {

    abstract int getRateOfInterest();

    abstract void withdraw(int amount);

    abstract void deposit(String accountNumber, int amount);
//    abstract void kyc(); // Every subclass will need to implement this method

    public void openAccount(String accountType) {
        System.out.println("Account of type " + accountType + " opened.");
    }

    public void closeAccount(String accountNumber) {
        System.out.println("Account " + accountNumber + " closed.");
    }

    public static void getRepoRate() {
        System.out.println("Repo rate is6.5%");
    }

    int minBalance = 1000;

    public final void bankPolicy() {
        System.out.println("All banks must follow RBI guidelines.");
    }


}

class SBI extends Bank {

    @Override
    int getRateOfInterest() {
        return 6;
    }

    @Override
    void withdraw(int amount) {
        // Logic to withdraw amount from SBI account
    }

    @Override
    void deposit(String accountNumber, int amount) {
        // Logic to deposit amount into SBI account
    }

    @Override
    public void closeAccount(String accountNumber) {
        // SBI specific logic to close account
        System.out.println("SBI Account " + accountNumber + " closed with additional formalities.");
    }

    // Not Allowed as bankPolicy is final in parent class
//    @Override
//    public void bankPolicy() {
//        // SBI specific logic to open account
//        System.out.println("SBI Account of type " + accountType + " opened with special benefits.");
//    }
}

class PNB extends Bank {

    @Override
    int getRateOfInterest() {
        return 7;
    }

    @Override
    void withdraw(int amount) {
        // Logic to withdraw amount from PNB account
    }

    @Override
    void deposit(String accountNumber, int amount) {
        // Logic to deposit amount into PNB account
    }
}
