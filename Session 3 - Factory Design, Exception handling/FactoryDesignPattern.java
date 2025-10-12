public class FactoryDesignPattern {

    public static void main(String[] args) {
        ATMFactory atmFactory = new ATMFactory();

        // read input from card
        String inputCardBank = "HDFC";
        ATM atm = atmFactory.getATMObject(inputCardBank);
        atm.withdraw();
        atm.deposit();
        atm.checkBalance();
    }
}

interface ATM {
    void withdraw();

    void deposit();

    void checkBalance();
}

class SBIAtm implements ATM {

    @Override
    public void withdraw() {
        System.out.println("Withdraw from SBI Account");
    }

    @Override
    public void deposit() {
        System.out.println("Deposit to SBI Account");
    }

    @Override
    public void checkBalance() {
        System.out.println("Checking balance....");
    }
}

class HDFCAtm implements ATM {

    @Override
    public void withdraw() {
        System.out.println("Withdraw from HDFC Account");
    }

    @Override
    public void deposit() {
        System.out.println("Deposit to HDFC Account");
    }

    @Override
    public void checkBalance() {
        System.out.println("Checking balance from hdfc account....");
    }
}

class ATMFactory {
    // Factory method
    public ATM getATMObject(String cardBankName) {
        if (cardBankName.equalsIgnoreCase("SBI")) {
            return new SBIAtm();
        } else if (cardBankName.equalsIgnoreCase("HDFC")) {
            return new HDFCAtm();
        } else {
            return null;
        }
    }
}
