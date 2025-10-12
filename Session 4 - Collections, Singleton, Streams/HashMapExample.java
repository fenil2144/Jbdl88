import java.util.HashMap;
import java.util.Map;

public class HashMapExample {

    public static void main(String[] args) {
        Map<Long, String> phoneDirectory = new HashMap<>();
        phoneDirectory.put(9878788789L, "Alice");
        phoneDirectory.put(9876767676L,"Nobita");
        phoneDirectory.put(7867676767L, "Shizuka");

        System.out.println(phoneDirectory);

        for(Long phoneNumber : phoneDirectory.keySet()){
            System.out.println("Phone Number: " + phoneNumber + ", Name: " + phoneDirectory.get(phoneNumber));
        }
        System.out.println("Using entry set");
        for(Map.Entry<Long, String> entry: phoneDirectory.entrySet()){
            System.out.println("Phone Number: " + entry.getKey() + ", Name: " + entry.getValue());
        }
    }
}
