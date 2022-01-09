package use_cases.member.exposition;

import kernel.CommandBus;
import kernel.QueryBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import use_cases.member.application.CreateMember;
import use_cases.member.application.RetrieveMembers;
import use_cases.member.domain.EmailAddress;
import use_cases.member.domain.Member;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
public class MemberController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public MemberController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @GetMapping(path = "/members", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MembersResponse> getAll() {
        final List<Member> members = queryBus.send(new RetrieveMembers());
        MembersResponse membersResponseResult = new MembersResponse(members.stream().map(
           member -> new MemberResponse(
                   String.valueOf(member.getMemberId()),
                   member.getLastname(),
                   member.getFirstname(),
                   new EmailAddressResponse(member.getEmail().getValue()),
                   member.getPassword(),
                   new SubscriptionResponse(member.getSubscription().subscriptionType(), member.getSubscription().pricePerMonth())
           )
        ).collect(Collectors.toList()));
        return ResponseEntity.ok(membersResponseResult);
    }

    @PostMapping(path = "/members", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid MemberRequest request) {
        CreateMember createMember = new CreateMember(request.lastname, request.firstname, new EmailAddress(request.emailAddress.email), request.password, request.subscription.type);
        this.commandBus.send(createMember);
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