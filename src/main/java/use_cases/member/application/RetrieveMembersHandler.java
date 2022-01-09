package use_cases.member.application;

import kernel.QueryHandler;
import use_cases.member.domain.Member;
import use_cases.member.domain.MemberRepository;

import java.util.List;

public class RetrieveMembersHandler implements QueryHandler<RetrieveMembers, List<Member>> {

    private final MemberRepository memberRepository;

    public RetrieveMembersHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> handle(final RetrieveMembers query) {
        return this.memberRepository.findAll();
    }
}