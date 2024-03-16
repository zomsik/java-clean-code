package pollub.projekt.ddd.common.patterns.observer;

import pollub.projekt.ddd.account.infrastructure.jpa.AccountEntity;

public interface NewAccountListener {
    void onAccountCreated(AccountEntity account);
}