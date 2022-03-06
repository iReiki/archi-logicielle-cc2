package projectAl.use_cases.payment.application;

import projectAl.kernel.QueryHandler;
import projectAl.use_cases.payment.domain.CreditCard;
import projectAl.use_cases.payment.domain.CreditCardRepository;

import java.util.Arrays;
import java.util.List;

public class RetrieveCreditCardByIdHandler implements QueryHandler<RetrieveCreditCardById, List<CreditCard>> {

    private final CreditCardRepository creditCardRepository;

    public RetrieveCreditCardByIdHandler(final CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public List<CreditCard> handle(final RetrieveCreditCardById query) {
        return List.of(creditCardRepository.findById(query.id));
    }
}
