package projectAl.kernel.exception;

public class InvalidCreditCardFormatException extends IllegalArgumentException{

    public InvalidCreditCardFormatException(String creditCard) {
        super(String.format("Invalid format for the credit card %s.", creditCard));
    }

}
