package projectAl.use_cases.payment.application;

import projectAl.kernel.Command;

import java.util.Date;

public class CreateCreditCard implements Command {

    public final String cardNumber;
    public final String expirationDate;
    public final String cvcCode;

    public CreateCreditCard(final String cardNumber, final String expirationDate, final String cvcCode) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvcCode = cvcCode;
    }
}
