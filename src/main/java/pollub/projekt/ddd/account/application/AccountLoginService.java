package pollub.projekt.ddd.account.application;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pollub.projekt.ddd.account.domain.AccountRepository;
import pollub.projekt.ddd.account.rest.dto.LoginRequestDto;
import pollub.projekt.ddd.account.rest.dto.Response;
import pollub.projekt.ddd.common.patterns.factory.ResponseEnum;
import pollub.projekt.ddd.common.patterns.factory.ResponseFactory;
import pollub.projekt.ddd.common.patterns.mediator.LoggerMediator;
import pollub.projekt.ddd.common.utils.JwtUtil;

@Service
public class AccountLoginService {

    private final JwtUtil jwtUtil;
    private final AccountRepository accountRepository;
    private final LoggerMediator loggerMediator;

    public AccountLoginService(AccountRepository accountRepository, LoggerMediator loggerMediator) {
        this.loggerMediator = loggerMediator;
        this.jwtUtil = JwtUtil.getInstance();
        this.accountRepository = accountRepository;
    }

    public Response login(LoginRequestDto request) {
        if (accountRepository.existsByLogin(request.getLogin())) {

            String passHash = accountRepository.getPasswordByLogin(request.getLogin());
            if (BCrypt.checkpw(request.getPassword(), passHash)) {
                String jwt = jwtUtil.createJWT(request.getLogin());
                if (jwt != null) {
                    loggerMediator.notify(this, "Login response created successfully");
                    return ResponseFactory.createResponse(ResponseEnum.LOGIN, true, "Zalogowano" , jwt);

                } else {

                    return ResponseFactory.createResponse(ResponseEnum.LOGIN, false, "Błąd logowania", null);
                }
            } else {
                return ResponseFactory.createResponse(ResponseEnum.LOGIN, false, "Niepoprawne hasło", null);
            }

        } else {
            return ResponseFactory.createResponse(ResponseEnum.LOGIN, false, "Nie znaleziono użytkownika o takim loginie i haśle", null);
        }
    }
}
