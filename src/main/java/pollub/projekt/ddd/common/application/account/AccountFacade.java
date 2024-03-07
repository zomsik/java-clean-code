package pollub.projekt.ddd.common.application.account;


import pollub.projekt.ddd.account.rest.dto.LoginRequestDto;
import pollub.projekt.ddd.account.rest.dto.RegisterRequestDto;
import pollub.projekt.ddd.account.rest.dto.Response;

import java.time.LocalDateTime;


/* Tydzień 4, Wzorzec Facade

 Fasada pełni funkcję dostępu do całej utworzonej domeny. Wzorzec facade ukrywa złożoność i szczegółową implementację
 funkcjonalności programu. Oferuje innym domenom systemu prosty interfejs do komunikacji.

Koniec, Tydzień 4, Wzorzec Facade */
public interface AccountFacade {


    Response login(LoginRequestDto request);

    Response register(RegisterRequestDto request);

    LocalDateTime getRegisterDateByLogin(String login);

    Integer getIdByLogin(String user);
}
