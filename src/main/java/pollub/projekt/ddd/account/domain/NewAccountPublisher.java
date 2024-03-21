package pollub.projekt.ddd.account.domain;

import pollub.projekt.ddd.account.infrastructure.jpa.AccountEntity;
import pollub.projekt.ddd.common.patterns.observer.NewAccountListener;

public interface NewAccountPublisher {
    void notify(AccountEntity t);
     void addListener(NewAccountListener listener);
     void removeListener(NewAccountListener listener);
}
