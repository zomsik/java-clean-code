package pollub.projekt.ddd.account.domain.exception;

import org.springframework.stereotype.Component;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.account.infrastructure.jpa.AccountEntity;
import pollub.projekt.ddd.common.patterns.observer.NewAccountNotifier;

import java.util.ArrayList;

@Component
public class AccountStorage{
    private ArrayList<AccountEntity> list;
    private final NewAccountNotifier newAccountNotifier;

    public AccountStorage(){
        list = new ArrayList<>();
        newAccountNotifier = new NewAccountNotifier();
    }
    public void add(AccountEntity t){
        list.add(t);
        newAccountNotifier.onAccountCreated(t);
    }
    public void remove(AccountEntity t){
        list.remove(t);
    }
}
