public class SingletonDesignPattern {


private SingletonDesignPattern(){
    // private constructor
}
    private static SingletonDesignPattern instance;

    static {
        System.out.println("From Static Method: " + instance);
    }

    public static SingletonDesignPattern getInstance(){
        if (instance == null) {
            synchronized(SingletonDesignPattern.class){
                if(instance == null){
                    instance = new SingletonDesignPattern();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime());
        SingletonDesignPattern singletonDesignPattern = new SingletonDesignPattern();
        SingletonDesignPattern.getInstance(); // Right Way
        System.out.println("From main method: " + singletonDesignPattern);
    }
}
