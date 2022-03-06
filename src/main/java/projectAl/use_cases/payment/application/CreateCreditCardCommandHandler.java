package projectAl.use_cases.payment.application;

import projectAl.kernel.CommandHandler;
import projectAl.kernel.Event;
import projectAl.kernel.EventDispatcher;
import projectAl.use_cases.payment.domain.CreditCard;
import projectAl.use_cases.payment.domain.CreditCardId;
import projectAl.use_cases.payment.domain.CreditCardRepository;

import java.text.ParseException;

public class CreateCreditCardCommandHandler implements CommandHandler<CreateCreditCard, CreditCardId> {

    private final CreditCardRepository creditCardRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateCreditCardCommandHandler(final CreditCardRepository creditCardRepository, final EventDispatcher<Event> eventEventDispatcher) {
        this.creditCardRepository = creditCardRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public CreditCardId handle(final CreateCreditCard createCreditCard) {
        final CreditCardId creditCardId = creditCardRepository.nextIdentity();
        CreditCard creditCard = null;
        try {
            creditCard = CreditCard.of(creditCardId, createCreditCard.cardNumber, createCreditCard.expirationDate, createCreditCard.cvcCode);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        creditCardRepository.add(creditCard);
        eventEventDispatcher.dispatch(new CreateCreditCardEvent(creditCardId));
        return creditCardId;
    }
}
