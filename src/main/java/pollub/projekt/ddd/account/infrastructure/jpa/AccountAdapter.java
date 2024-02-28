package pollub.projekt.ddd.account.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.account.domain.AccountRepository;

import java.time.LocalDateTime;


@Repository
@Primary
@AllArgsConstructor
public class AccountAdapter implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

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
        return accountJpaRepository.save(account.translate()).translate();
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
