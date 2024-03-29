package pollub.projekt.ddd.account.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.projekt.ddd.account.rest.dto.LoginRequestDto;
import pollub.projekt.ddd.account.rest.dto.RegisterRequestDto;
import pollub.projekt.ddd.account.rest.dto.Response;
import pollub.projekt.ddd.common.application.account.AccountFacade;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AccountFacadeImpl implements AccountFacade {

    private final GetAccountService getAccountService;
    private final AccountLoginService accountLoginService;
    private final AccountRegisterService accountRegisterService;

    @Override
    public Response login(LoginRequestDto request) {
        return accountLoginService.login(request);
    }

    @Override
    public Response register(RegisterRequestDto request) {
        return accountRegisterService.register(request);
    }

    @Override
    public LocalDateTime getRegisterDateByLogin(String login) {
        return getAccountService.getRegisterDateByLogin(login);
    }

    @Override
    public Integer getIdByLogin(String user) {
        return getAccountService.getIdByLogin(user);
    }
}
