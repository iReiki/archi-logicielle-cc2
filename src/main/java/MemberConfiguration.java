import kernel.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import use_cases.member.application.*;
import use_cases.member.domain.MemberRepository;
import use_cases.member.infrastructure.DefaultEventDispatcher;
import use_cases.member.infrastructure.InMemoryMemberRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MemberConfiguration {

    @Bean
    public MemberRepository memberRepository() {
        return new InMemoryMemberRepository();
    }

    @Bean
    public EventDispatcher<Event> eventEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(ModifyMemberSubscriptionEvent.class, List.of(new ModifyMemberSubscriptionEventListener()));
        listenerMap.put(CreateMemberEvent.class, List.of(new CreateMemberEventListener()));
        return new DefaultEventDispatcher(listenerMap);
    }

    @Bean
    public CreateMemberCommandHandler createMemberCommandHandler() {
        return new CreateMemberCommandHandler(memberRepository(), eventEventDispatcher());
    }

    @Bean
    public ModifyMemberSubscriptionCommandHandler modifyMemberSubscriptionCommandHandler() {
        return new ModifyMemberSubscriptionCommandHandler(memberRepository(), eventEventDispatcher());
    }

    @Bean
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap =
                Collections.singletonMap(CreateMember.class, new CreateMemberCommandHandler(memberRepository(), eventEventDispatcher()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap =
                Collections.singletonMap(RetrieveMembers.class, new RetrieveMembersHandler(memberRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }

    @Bean
    public RetrieveMembersHandler retrieveMembersHandler() {
        return new RetrieveMembersHandler(memberRepository());
    }

    @Bean
    public RetrieveMembersBySubscriptionHandler retrieveMembersBySubscriptionHandler() {
        return new RetrieveMembersBySubscriptionHandler(memberRepository());
    }
}