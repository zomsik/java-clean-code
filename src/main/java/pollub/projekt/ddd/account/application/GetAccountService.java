package pollub.projekt.ddd.account.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.projekt.ddd.account.domain.AccountRepository;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class GetAccountService {

    private final AccountRepository accountRepository;

    public LocalDateTime getRegisterDateByLogin(String login) {

        return accountRepository.getRegisterDateByLogin(login);
    }

    public Integer getIdByLogin(String user) {
        return accountRepository.getIdByLogin(user);
    }
}
