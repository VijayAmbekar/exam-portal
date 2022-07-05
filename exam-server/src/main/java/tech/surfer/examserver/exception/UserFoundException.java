package tech.surfer.examserver.exception;

public class UserFoundException extends Exception {

    UserFoundException() {
        super("User with this username already exist in database");
    }

    public UserFoundException(String message) {
        super(message);
    }

    public UserFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
