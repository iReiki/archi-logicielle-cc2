package use_cases.member.application;

import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;
import use_cases.member.domain.MemberId;
import use_cases.member.domain.MemberRepository;
import use_cases.member.domain.Subscription;
import use_cases.member.domain.SubscriptionType;
import use_cases.member.infrastructure.BusinessSubscription;
import use_cases.member.infrastructure.DefaultSubscription;
import use_cases.member.infrastructure.PremiumSubscription;

public class ModifyMemberSubscriptionCommandHandler implements CommandHandler<ModifyMemberSubscription, Void> {

    private final MemberRepository memberRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public ModifyMemberSubscriptionCommandHandler(MemberRepository memberRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.memberRepository = memberRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public Void handle(ModifyMemberSubscription command) {
        var memberId = MemberId.of(command.memberId);
        var member = this.memberRepository.findById(memberId);
        Subscription subscription = new DefaultSubscription();
        if (command.subscription.type.equals(SubscriptionType.BUSINESS.name())) {
            subscription = new BusinessSubscription();
        } else if (command.subscription.type.equals(SubscriptionType.PREMIUM.name())) {
            subscription = new PremiumSubscription();
        }
        member.changeSubscription(subscription);
        this.memberRepository.add(member);
        this.eventEventDispatcher.dispatch(new ModifyMemberSubscriptionEvent(memberId));
        return null;
    }
}
