package pollub.projekt.ddd.common.patterns.visitor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pollub.projekt.ddd.account.domain.CachedAccountRepository;
import pollub.projekt.ddd.account.infrastructure.jpa.AccountAdapter;


/* Tydzień 6, Wzorzec Visitor

 Visitor pozwala na dodanie nowych operacji do obiektu nie zmieniając struktury obiektu. Oddziela algorytmu od obiektu na którym pracują -
 implementacje znajdują się w klasie implementującej po AccountVisitor czyli w AccountVisitorImpl. Klasy visitowane to te implementujące VisitElement.

Koniec, Tydzień 6, Wzorzec Visitor */

@Component
@Slf4j
@AllArgsConstructor
public class AccountVisitorImpl implements AccountVisitor {

    private final AccountAdapter accountAdapter;
    private final CachedAccountRepository cachedAccountRepository;

    @Override
    public int visit(CachedAccountRepository cachedAccountRepository) {
        return cachedAccountRepository.getCachedLogins().size() + cachedAccountRepository.getCachedUserIds().size()
                + cachedAccountRepository.getCachedRegistedDates().size();
    }

    @Override
    public int visit(AccountAdapter accountAdapter) {
        return accountAdapter.getRegisterStorage().getList().size();
    }


    @Scheduled(cron= "0/15 * * ? * *")
    public void getCacheSizes() {
        log.info("Cache sizes = {}", this.cachedAccountRepository.accept(this) + this.accountAdapter.accept(this));
    }


}
