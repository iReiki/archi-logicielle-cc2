package projectAl.use_cases.member.application;

import projectAl.kernel.QueryHandler;
import projectAl.use_cases.member.domain.Member;
import projectAl.use_cases.member.domain.MemberRepository;

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