package projectAl.use_cases.payment.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreditCardRequest {

    @NotNull
    @NotBlank
    public String cardNumber;

    @NotNull
    @NotBlank
    public String expirationDate;

    @NotNull
    @NotBlank
    public String cvcCode;

    @Override
    public String toString() {
        return "CreditCardDTO{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvcCode='" + cvcCode + '\'' +
                '}';
    }
}
