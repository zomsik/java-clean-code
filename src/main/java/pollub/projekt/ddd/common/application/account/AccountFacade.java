package pollub.projekt.ddd.common.application.account;


import pollub.projekt.ddd.account.rest.dto.LoginRequestDto;
import pollub.projekt.ddd.account.rest.dto.LoginResponseDto;
import pollub.projekt.ddd.account.rest.dto.RegisterRequestDto;
import pollub.projekt.ddd.account.rest.dto.RegisterResponseDto;

import java.time.LocalDateTime;

public interface AccountFacade {


    LoginResponseDto login(LoginRequestDto request);

    RegisterResponseDto register(RegisterRequestDto request);

    LocalDateTime getRegisterDateByLogin(String login);

    Integer getIdByLogin(String user);
}
