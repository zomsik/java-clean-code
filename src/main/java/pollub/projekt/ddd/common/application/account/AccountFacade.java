package pollub.projekt.ddd.common.application.account;


import pollub.projekt.ddd.account.rest.dto.LoginRequestDto;
import pollub.projekt.ddd.account.rest.dto.RegisterRequestDto;
import pollub.projekt.ddd.common.patterns.factory.ResponseInterface;

import java.time.LocalDateTime;

public interface AccountFacade {


    ResponseInterface login(LoginRequestDto request);

    ResponseInterface register(RegisterRequestDto request);

    LocalDateTime getRegisterDateByLogin(String login);

    Integer getIdByLogin(String user);
}
