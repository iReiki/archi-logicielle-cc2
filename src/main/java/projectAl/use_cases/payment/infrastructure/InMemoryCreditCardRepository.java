package projectAl.use_cases.payment.infrastructure;

import projectAl.kernel.exception.NoSuchEntityException;
import projectAl.use_cases.payment.domain.CreditCard;
import projectAl.use_cases.payment.domain.CreditCardId;
import projectAl.use_cases.payment.domain.CreditCardRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryCreditCardRepository implements CreditCardRepository {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<Integer, CreditCard> data = new ConcurrentHashMap<>();

    @Override
    public CreditCardId nextIdentity() {
        return CreditCardId.of(counter.incrementAndGet());
    }

    @Override
    public CreditCard findById(final CreditCardId id) throws NoSuchEntityException {
        final CreditCard creditCard = data.get(id.getValue());
        if (creditCard == null) {
            throw NoSuchEntityException.withId(id);
        }
        return creditCard;
    }

    @Override
    public void add(final CreditCard entity) {
        data.put(entity.id().getValue(), entity);
    }

    @Override
    public void removeById(final CreditCardId id) {
        data.remove(id.getValue());
    }
}
