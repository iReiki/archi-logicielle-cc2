package use_cases.member.application;

import kernel.QueryHandler;
import use_cases.member.domain.Member;
import use_cases.member.domain.MemberRepository;

import java.util.List;

public class RetrieveMembersBySubscriptionHandler implements QueryHandler<RetrieveMembersBySubscription, List<Member>> {

    private final MemberRepository memberRepository;

    public RetrieveMembersBySubscriptionHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> handle(final RetrieveMembersBySubscription query) {
        return this.memberRepository.findBySubscription(query.subscriptionType);
    }
}