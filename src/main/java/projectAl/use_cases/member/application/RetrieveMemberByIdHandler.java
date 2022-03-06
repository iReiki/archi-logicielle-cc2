package projectAl.use_cases.member.application;

import projectAl.kernel.QueryHandler;
import projectAl.use_cases.member.domain.Member;
import projectAl.use_cases.member.domain.MemberRepository;

import java.util.List;

public class RetrieveMemberByIdHandler implements QueryHandler<RetrieveMemberById, List<Member>> {

    private final MemberRepository memberRepository;

    public RetrieveMemberByIdHandler(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> handle(final RetrieveMemberById query) {
        return List.of(this.memberRepository.findById(query.id));
    }
}
