package projectAl.use_cases.payment.domain;

import projectAl.kernel.Entity;
import projectAl.kernel.exception.InvalidCreditCardFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditCard implements Entity<CreditCardId> {

    private final CreditCardId creditCardId;
    private final String cardNumber;
    private final Date expirationDate;
    private final String cvcCode;

    private CreditCard(final CreditCardId creditCardId, final String cardNumber, final String expirationDate, final String cvcCode) throws ParseException {
        this.creditCardId = creditCardId;
        if (this.checkCardNumberFormat(cardNumber) && this.checkExpirationDate(expirationDate) && this.checkCvcCode(cvcCode)) {
            this.cardNumber = cardNumber;
            this.cvcCode = cvcCode;
            this.expirationDate = new SimpleDateFormat("yy-MM").parse(expirationDate);
        } else {
            throw new InvalidCreditCardFormatException(cardNumber);
        }
    }

    public static CreditCard of(final CreditCardId creditCardId, final String cardNumber, final String expirationDate, final String cvcCode) throws ParseException {
        return new CreditCard(creditCardId, cardNumber, expirationDate, cvcCode);
    }

    private boolean checkCardNumberFormat(String cardNumber) {
        return cardNumber.length() == 16;
    }

    private boolean checkCvcCode(String cvc) {
        return cvc.length() == 3;
    }

    private boolean checkExpirationDate(String expirationDate) {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yy-MM");
            Date expDate = dt.parse(expirationDate);
            Date actualDate = new Date();

            return expDate.compareTo(actualDate) > 0;

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getCvcCode() {
        return cvcCode;
    }

    @Override
    public CreditCardId id() {
        return this.creditCardId;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardId=" + creditCardId.getValue() +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate+
                ", cvcCode='" + cvcCode + '\'' +
                '}';
    }
}

