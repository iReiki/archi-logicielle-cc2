package projectAl.use_cases.member.application;

import projectAl.kernel.CommandHandler;
import projectAl.kernel.Event;
import projectAl.kernel.EventDispatcher;
import projectAl.use_cases.member.domain.EmailAddress;
import projectAl.use_cases.member.domain.Member;
import projectAl.use_cases.member.domain.MemberId;
import projectAl.use_cases.member.domain.MemberRepository;

public final class CreateMemberCommandHandler implements CommandHandler<CreateMember, MemberId> {

    private final MemberRepository memberRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateMemberCommandHandler(MemberRepository memberRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.memberRepository = memberRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public MemberId handle(final CreateMember createMember) {
        final MemberId memberId = memberRepository.nextIdentity();
        Member member = Member.of(memberId, createMember.lastname, createMember.firstname, new EmailAddress(createMember.emailAddress.getValue()),
                createMember.password, createMember.subscription);
        memberRepository.add(member);
        eventEventDispatcher.dispatch(new CreateMemberEvent(memberId));
        return memberId;
    }
}
