package projectAl.use_cases.payment.exposition;

import projectAl.use_cases.member.domain.MemberId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentRequest {

    @NotNull
    @NotBlank
    public MemberId memberId;

    @NotNull
    public CreditCardRequest creditCard;

}
