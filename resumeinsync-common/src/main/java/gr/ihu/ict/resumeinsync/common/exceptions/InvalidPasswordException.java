package gr.ihu.ict.resumeinsync.common.exceptions;

public class InvalidPasswordException extends Exception {

    public InvalidPasswordException() {
        this("Invalid password");
    }

    public InvalidPasswordException(final String message) {
        super(message);
    }
}
