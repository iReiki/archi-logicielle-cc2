package projectAl.use_cases.member.infrastructure;

import projectAl.kernel.exception.NoSuchEntityException;
import projectAl.use_cases.member.domain.Member;
import projectAl.use_cases.member.domain.MemberId;
import projectAl.use_cases.member.domain.MemberRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class InMemoryMemberRepository implements MemberRepository {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<Integer, Member> data = new ConcurrentHashMap<>();

    @Override
    public void removeById(final MemberId memberId) { data.remove(memberId.getValue()); }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public Member findById(final MemberId memberId) {
        final Member member = data.get(memberId.getValue());
        if (member == null) {
            throw NoSuchEntityException.withId(memberId);
        }
        return member;
    }

    @Override
    public MemberId nextIdentity() {
        return MemberId.of(counter.incrementAndGet());
    }


    @Override
    public void add(final Member member) {
        data.put(member.getMemberId().getValue(), member);
    }


    @Override
    public List<Member> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public List<Member> findBySubscription(final String type) {
        return List.copyOf(data.values().stream()
                .filter(member -> member.getSubscription().subscriptionType().equals(type)).collect(Collectors.toList()));
    }
}
