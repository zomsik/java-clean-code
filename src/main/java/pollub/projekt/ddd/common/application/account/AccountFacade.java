package pollub.projekt.ddd.common.application.account;


import pollub.projekt.ddd.account.rest.dto.LoginRequestDto;
import pollub.projekt.ddd.account.rest.dto.RegisterRequestDto;
import pollub.projekt.ddd.account.rest.dto.Response;

import java.time.LocalDateTime;

public interface AccountFacade {


    Response login(LoginRequestDto request);

    Response register(RegisterRequestDto request);

    LocalDateTime getRegisterDateByLogin(String login);

    Integer getIdByLogin(String user);
}
