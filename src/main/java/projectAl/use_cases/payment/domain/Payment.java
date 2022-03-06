package projectAl.use_cases.payment.domain;

import projectAl.kernel.Entity;
import projectAl.use_cases.member.domain.Member;
import projectAl.use_cases.member.domain.MemberId;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment implements Entity<PaymentId> {

    private final PaymentId paymentId;
    private final Member member;
    private final CreditCard creditCard;
    private final String paymentDate;

    private Payment(final PaymentId paymentId, final Member member, final CreditCard creditCard) {
        this.paymentId = paymentId;
        this.member = member;
        this.creditCard = creditCard;
        this.paymentDate = new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(new Date());
    }

    public static Payment of(final PaymentId paymentId, final Member member, final CreditCard creditCard) {
        return new Payment(paymentId, member, creditCard);
    }

    @Override
    public PaymentId id() {
        return this.paymentId;
    }

    public Member getMember() {
        return member;
    }

    public CreditCard getCreditCard() { return this.creditCard; }

    public String getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId.getValue() +
                ", member=" + member.toString() +
                ", creditCard=" + creditCard.toString() +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
