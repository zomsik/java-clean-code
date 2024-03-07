package pollub.projekt.ddd.account.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pollub.projekt.ddd.account.domain.exception.AccountErrorResponseBody;
import pollub.projekt.ddd.account.domain.exception.AccountException;
import pollub.projekt.ddd.account.rest.dto.AccountErrorResponse;
import pollub.projekt.ddd.account.rest.dto.LoginRequestDto;
import pollub.projekt.ddd.account.rest.dto.RegisterRequestDto;
import pollub.projekt.ddd.common.application.account.AccountFacade;

import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping(path = "/account")
@RestController
@Slf4j
@Valid
@CrossOrigin
public class AccountController {


    private final AccountFacade accountFacade;

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<AccountErrorResponseBody> handleAccountException(AccountException ex) {
        log.error(ex.getMessage());
        return AccountErrorResponse.fromAccountException(ex).asResponseEntity();
    }

    @GetMapping(path = "/register-date-by-login")
    public ResponseEntity<LocalDateTime> getRegisterDateByLogin(@RequestParam(value = "login") String login) {

        return ResponseEntity.ok(accountFacade.getRegisterDateByLogin(login));
    }


    @PostMapping(path = "/login", consumes={"application/json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid LoginRequestDto request) {

        return ResponseEntity.ok(accountFacade.login(request).body());
    }

    @PostMapping(path = "/register", consumes={"application/json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Object>> register(@RequestBody @Valid RegisterRequestDto request) {

        return ResponseEntity.ok(accountFacade.register(request).body());
    }


}
