package projectAl.use_cases.payment.application;

import projectAl.kernel.Query;
import projectAl.use_cases.member.domain.MemberId;

public class RetrievePaymentsByMember implements Query {

    public final MemberId id;

    public RetrievePaymentsByMember(MemberId id) {
        this.id = id;
    }

}
