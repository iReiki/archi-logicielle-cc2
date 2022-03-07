package projectAl.use_cases.payment.exposition;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import projectAl.kernel.CommandBus;
import projectAl.kernel.QueryBus;
import projectAl.use_cases.member.application.RetrieveMemberById;
import projectAl.use_cases.member.domain.Member;
import projectAl.use_cases.member.exposition.EmailAddressResponse;
import projectAl.use_cases.member.exposition.MemberResponse;
import projectAl.use_cases.member.exposition.SubscriptionResponse;
import projectAl.use_cases.payment.application.CreateCreditCard;
import projectAl.use_cases.payment.application.CreatePayment;
import projectAl.use_cases.payment.application.RetrieveCreditCardById;
import projectAl.use_cases.payment.application.RetrievePayments;
import projectAl.use_cases.payment.domain.CreditCard;
import projectAl.use_cases.payment.domain.CreditCardId;
import projectAl.use_cases.payment.domain.Payment;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
public class PaymentController {

    private final CommandBus paymentCommandBus;
    private final QueryBus paymentQueryBus;
    private final CommandBus creditCardCommandBus;
    private final QueryBus creditCardQueryBus;
    private final CommandBus memberCommandBus;
    private final QueryBus memberQueryBus;

    public PaymentController(
            @Qualifier("paymentCommandBus") CommandBus pCommandBus,
            @Qualifier("paymentQueryBus") QueryBus pQueryBus,
            @Qualifier("creditCardCommandBus") CommandBus cCommandBus,
            @Qualifier("creditCardQueryBus") QueryBus cQueryBus,
            @Qualifier("memberCommandBus") CommandBus mCommandBus,
            @Qualifier("memberQueryBus") QueryBus mQueryBus) {
        this.paymentCommandBus = pCommandBus;
        this.paymentQueryBus = pQueryBus;
        this.creditCardCommandBus = cCommandBus;
        this.creditCardQueryBus = cQueryBus;
        this.memberCommandBus = mCommandBus;
        this.memberQueryBus = mQueryBus;
    }

    @GetMapping(path = "/payments", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PaymentsResponse> getAll() {
        final List<Payment> payments = paymentQueryBus.send(new RetrievePayments());
        PaymentsResponse paymentsResponseResult = new PaymentsResponse(payments.stream().map(
            payment -> new PaymentResponse(
                    String.valueOf(payment.id().getValue()),
                    new MemberResponse(
                            payment.getMember().id().getStringValue(),
                            payment.getMember().getLastname(),
                            payment.getMember().getFirstname(),
                            new EmailAddressResponse(payment.getMember().getEmail().getValue()),
                            payment.getMember().getPassword(),
                            new SubscriptionResponse(
                                    payment.getMember().getSubscription().subscriptionType(),
                                    payment.getMember().getSubscription().pricePerMonth()
                            )
                    ),
                    new CreditCardResponse(
                            payment.getCreditCard().getCardNumber(),
                            payment.getCreditCard().getExpirationDate().toString(),
                            payment.getCreditCard().getCvcCode()
                    ),
                    payment.getPaymentDate()
            )
        ).collect(Collectors.toList()));
        return ResponseEntity.ok(paymentsResponseResult);
    }

    @PostMapping(path = "/payments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid PaymentRequest request) {
        CreateCreditCard createCreditCard = new CreateCreditCard(request.creditCard.cardNumber, request.creditCard.expirationDate, request.creditCard.cvcCode);
        CreditCardId creditCardId = this.creditCardCommandBus.send(createCreditCard);

        final List<Member> members = this.memberQueryBus.send(new RetrieveMemberById(request.memberId));
        Member member = members.get(0);

        final List<CreditCard> creditCards = this.creditCardQueryBus.send(new RetrieveCreditCardById(creditCardId));
        CreditCard creditCard = creditCards.get(0);

        CreatePayment createPayment = new CreatePayment(member, creditCard);
        this.paymentCommandBus.send(createPayment);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
