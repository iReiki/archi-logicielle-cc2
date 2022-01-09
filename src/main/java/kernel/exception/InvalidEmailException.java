package kernel.exception;

public class InvalidEmailException extends IllegalArgumentException {

    public InvalidEmailException(String emailAddress) {
        super(String.format("Invalid format for the email %s.", emailAddress));
    }

}
