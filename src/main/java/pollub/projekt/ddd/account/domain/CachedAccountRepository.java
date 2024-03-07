package pollub.projekt.ddd.account.domain;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
@Primary
public class CachedAccountRepository implements AccountRepository {
    private final AccountRepository accountRepository;
    private final Set<String> cachedLogins;
    private final Map<String, LocalDateTime> cachedRegistedDates;
    private final Map<String, Integer> cachedUserIds;

    public CachedAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        cachedLogins = new HashSet<>();
        cachedRegistedDates = new HashMap<>();
        cachedUserIds = new HashMap<>();
    }

    @Override
    public boolean existsByLogin(String login) {
        if(cachedLogins.contains(login)) {
            return true;
        }

        boolean result = accountRepository.existsByLogin(login);
        if(result) {
            cachedLogins.add(login);
        }
        return result;

    }

    @Override
    public String getPasswordByLogin(String login) {
        return accountRepository.getPasswordByLogin(login);
    }

    @Override
    public Account save(Account build) {
        return accountRepository.save(build);
    }

    @Override
    public LocalDateTime getRegisterDateByLogin(String login) {
        if(cachedRegistedDates.containsKey(login)) {
            return cachedRegistedDates.get(login);
        }

        LocalDateTime registerDate = accountRepository.getRegisterDateByLogin(login);
        cachedRegistedDates.put(login, registerDate);
        return registerDate;
    }

    @Override
    public Integer getIdByLogin(String user) {
        if(cachedUserIds.containsKey(user)) {
            return cachedUserIds.get(user);
        }

        Integer userId = accountRepository.getIdByLogin(user);
        if(userId != null) {
            cachedUserIds.put(user, userId);
        }
        return userId;
    }
}
