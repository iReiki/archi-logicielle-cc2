package projectAl.use_cases.payment.application;

import projectAl.kernel.Command;
import projectAl.use_cases.member.domain.Member;
import projectAl.use_cases.member.domain.MemberId;
import projectAl.use_cases.payment.domain.CreditCard;

import java.util.Date;

public class CreatePayment implements Command {

    public final Member member;
    public final CreditCard creditCard;
    public final Date paymentDate;

    public CreatePayment(final Member member, final CreditCard creditCard) {
        this.member = member;
        this.creditCard = creditCard;
        this.paymentDate = new Date();
    }
}
