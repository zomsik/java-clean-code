package pollub.projekt.ddd.account.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pollub.projekt.ddd.account.infrastructure.jpa.AccountEntity;
import pollub.projekt.ddd.common.patterns.observer.NewAccountListener;
import pollub.projekt.ddd.common.patterns.observer.NewAccountNotifier;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class AccountStorage implements NewAccountPublisher {

    private final ArrayList<AccountEntity> list;
    private final List<NewAccountListener> newAccountNotifiers;

    public AccountStorage(){
        list = new ArrayList<>();
        newAccountNotifiers = new ArrayList<>();
    }
    public void add(AccountEntity t){
        list.add(t);
        notify(t);
    }
    public void remove(AccountEntity t){
        list.remove(t);
    }

    public void notify(AccountEntity t) {
        for(var listener : newAccountNotifiers) {
            listener.onAccountCreated(t);
        }
    }

    public void addListener(NewAccountListener listener) {
        newAccountNotifiers.add(listener);
    }

    public void removeListener(NewAccountListener listener) {
        newAccountNotifiers.remove(listener);
    }
}
