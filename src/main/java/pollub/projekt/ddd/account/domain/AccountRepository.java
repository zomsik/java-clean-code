package pollub.projekt.ddd.account.domain;

import java.time.LocalDateTime;

public interface AccountRepository {

    boolean existsByLogin(String login);

    String getPasswordByLogin(String login);

    Account save(Account build);

    LocalDateTime getRegisterDateByLogin(String login);

    Integer getIdByLogin(String user);
}
