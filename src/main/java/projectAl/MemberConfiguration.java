package projectAl;

import projectAl.kernel.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import projectAl.use_cases.member.application.*;
import projectAl.use_cases.member.domain.MemberRepository;
import projectAl.use_cases.member.infrastructure.DefaultEventDispatcher;
import projectAl.use_cases.member.infrastructure.InMemoryMemberRepository;
import projectAl.use_cases.payment.application.*;
import projectAl.use_cases.payment.domain.CreditCardRepository;
import projectAl.use_cases.payment.domain.PaymentRepository;
import projectAl.use_cases.payment.infrastructure.InMemoryCreditCardRepository;
import projectAl.use_cases.payment.infrastructure.InMemoryPaymentRepository;

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
    public PaymentRepository paymentRepository() {
        return new InMemoryPaymentRepository();
    }

    @Bean
    public CreditCardRepository creditCardRepository() { return new InMemoryCreditCardRepository(); }

    @Bean
    public EventDispatcher<Event> eventEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(ModifyMemberSubscriptionEvent.class, List.of(new ModifyMemberSubscriptionEventListener()));
        listenerMap.put(CreateMemberEvent.class, List.of(new CreateMemberEventListener()));
        listenerMap.put(CreateCreditCardEvent.class, List.of(new CreateCreditCardEventListener()));
        listenerMap.put(CreatePaymentEvent.class, List.of(new CreatePaymentEventListener()));
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
    public CreatePaymentCommandHandler createPaymentCommandHandler() {
        return new CreatePaymentCommandHandler(paymentRepository(), eventEventDispatcher());
    }

    @Bean
    public CreateCreditCardCommandHandler createCreditCardCommandHandler() {
        return  new CreateCreditCardCommandHandler(creditCardRepository(), eventEventDispatcher());
    }

    @Bean
    public CommandBus memberCommandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap =
                Collections.singletonMap(CreateMember.class, new CreateMemberCommandHandler(memberRepository(), eventEventDispatcher()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus memberQueryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap =
                Collections.singletonMap(RetrieveMembers.class, new RetrieveMembersHandler(memberRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }

    @Bean
    public CommandBus creditCardCommandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap =
                Collections.singletonMap(CreateCreditCard.class, new CreateCreditCardCommandHandler(creditCardRepository(), eventEventDispatcher()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus creditCardQueryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap =
                Collections.singletonMap(RetrieveCreditCardById.class, new RetrieveCreditCardByIdHandler(creditCardRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }

    @Bean
    public CommandBus paymentCommandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap =
                Collections.singletonMap(CreatePayment.class, new CreatePaymentCommandHandler(paymentRepository(), eventEventDispatcher()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus paymentQueryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap =
                Collections.singletonMap(RetrievePayments.class, new RetrievePaymentsHandler(paymentRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }

    @Bean
    public RetrieveMemberByIdHandler retrieveMemberByIdHandler() {
        return new RetrieveMemberByIdHandler(memberRepository());
    }

    @Bean
    public RetrieveMembersHandler retrieveMembersHandler() {
        return new RetrieveMembersHandler(memberRepository());
    }

    @Bean
    public RetrieveMembersBySubscriptionHandler retrieveMembersBySubscriptionHandler() {
        return new RetrieveMembersBySubscriptionHandler(memberRepository());
    }

    @Bean
    public RetrieveCreditCardByIdHandler retrieveCreditCardByIdHandler() {
        return new RetrieveCreditCardByIdHandler(creditCardRepository());
    }

    @Bean
    public RetrievePaymentsHandler retrievePaymentsHandler() {
        return new RetrievePaymentsHandler(paymentRepository());
    }

    @Bean
    public RetrievePaymentsByMemberHandler retrievePaymentsByMemberHandler() {
        return new RetrievePaymentsByMemberHandler(paymentRepository());
    }

}