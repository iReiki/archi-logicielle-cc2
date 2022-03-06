package projectAl.use_cases.payment.exposition;

import projectAl.use_cases.member.exposition.MemberResponse;

public class PaymentResponse {

    public String id;
    public MemberResponse member;
    public CreditCardResponse creditCard;
    public String paymentDate;

    public PaymentResponse(String id, MemberResponse member, CreditCardResponse creditCard, String paymentDate) {
        this.id = id;
        this.member = member;
        this.creditCard = creditCard;
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id='" + id + '\'' +
                ", member=" + member +
                ", creditCard=" + creditCard +
                ", paymentDate='" + paymentDate + '\'' +
                '}';
    }
}
