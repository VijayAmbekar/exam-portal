package tech.surfer.examserver.exception;

public class UserNotFoundException extends Exception {

    UserNotFoundException() {
        super("User with this username not found in database");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
