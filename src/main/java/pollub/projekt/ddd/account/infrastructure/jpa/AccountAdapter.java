package pollub.projekt.ddd.account.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.account.domain.AccountRepository;
import pollub.projekt.ddd.account.domain.exception.AccountStorage;

import java.time.LocalDateTime;


@Repository
@AllArgsConstructor
public class AccountAdapter implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;
    private final AccountStorage registersStorage;


    @Override
    public boolean existsByLogin(String login) {
        return accountJpaRepository.existsByLogin(login);
    }

    @Override
    public String getPasswordByLogin(String login) {
        return accountJpaRepository.getPasswordByLogin(login);
    }

    @Override
    public Account save(Account account) {

        AccountEntity ae = accountJpaRepository.save(account.translate());
        registersStorage.add(ae);
        return ae.translate();
    }

    @Override
    public LocalDateTime getRegisterDateByLogin(String login) {
        return accountJpaRepository.getRegisterDateByLogin(login);
    }

    @Override
    public Integer getIdByLogin(String user) {
        return accountJpaRepository.getIdByLogin(user);
    }
}
