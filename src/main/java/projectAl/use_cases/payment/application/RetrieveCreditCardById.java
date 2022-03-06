package projectAl.use_cases.payment.application;

import projectAl.kernel.Query;
import projectAl.use_cases.payment.domain.CreditCardId;

public class RetrieveCreditCardById implements Query {

    public final CreditCardId id;

    public RetrieveCreditCardById(CreditCardId id) {
        this.id = id;
    }
}
