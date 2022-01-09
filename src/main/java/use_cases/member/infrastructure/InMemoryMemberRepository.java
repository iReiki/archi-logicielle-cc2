package use_cases.member.infrastructure;

import kernel.exception.NoSuchEntityException;
import use_cases.member.domain.Member;
import use_cases.member.domain.MemberId;
import use_cases.member.domain.MemberRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class InMemoryMemberRepository implements MemberRepository {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<MemberId, Member> data = new ConcurrentHashMap<>();

    @Override
    public void removeById(final MemberId memberId) { data.remove(memberId); }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public Member findById(final MemberId memberId) {
        final Member member = data.get(memberId);
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
        data.put(member.getMemberId(), member);
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
