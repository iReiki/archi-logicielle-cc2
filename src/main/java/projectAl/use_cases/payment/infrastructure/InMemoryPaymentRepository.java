package projectAl.use_cases.payment.infrastructure;

import projectAl.kernel.exception.NoSuchEntityException;
import projectAl.use_cases.member.domain.Member;
import projectAl.use_cases.member.domain.MemberId;
import projectAl.use_cases.payment.domain.Payment;
import projectAl.use_cases.payment.domain.PaymentId;
import projectAl.use_cases.payment.domain.PaymentRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryPaymentRepository implements PaymentRepository {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<Integer, Payment> data = new ConcurrentHashMap<>();

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public List<Payment> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public List<Payment> findByMemberId(final MemberId memberId) {
        return List.copyOf(data.values().stream()
                .filter(payment -> payment.getMember().id().equals(memberId)).collect(Collectors.toList()));
    }

    @Override
    public PaymentId nextIdentity() {
        return PaymentId.of(counter.incrementAndGet());
    }

    @Override
    public Payment findById(final PaymentId id) throws NoSuchEntityException {
        final Payment payment = data.get(id.getValue());
        if (payment == null) {
            throw NoSuchEntityException.withId(id);
        }
        return payment;
    }

    @Override
    public void add(final Payment entity) {
        data.put(entity.id().getValue(), entity);
    }

    @Override
    public void removeById(final PaymentId id) {
        data.remove(id.getValue());
    }
}
