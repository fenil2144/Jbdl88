import java.util.ArrayList;
import java.util.List;

class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }
}

public class CustomException{
    public static void main(String[] args) {
        try {
            // business logic
            throw new UserNotFoundException("User with the given ID does not exist.");
        } catch (UserNotFoundException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }
}
