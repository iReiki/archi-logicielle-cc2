package projectAl.use_cases.payment.exposition;

public class CreditCardResponse {

    public final String cardNumber;
    public final String expirationDate;
    public final String cvcCode;

    public CreditCardResponse(final String cardNumber, final String expirationDate, final String cvcCode) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvcCode = cvcCode;
    }

    @Override
    public String toString() {
        return "CreditCardDTO{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvcCode='" + cvcCode + '\'' +
                '}';
    }
}
