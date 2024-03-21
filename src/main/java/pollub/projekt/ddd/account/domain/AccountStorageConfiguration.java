package pollub.projekt.ddd.account.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pollub.projekt.ddd.common.patterns.observer.NewAccountNotifier;

@Configuration
public class AccountStorageConfiguration {
    @Autowired
    public void initAccountStorage(AccountStorage accountStorage) {
        accountStorage.addListener(new NewAccountNotifier());
    }
}
