package pollub.projekt.ddd.common.patterns.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pollub.projekt.ddd.account.infrastructure.jpa.AccountEntity;

/* Tydzień 5, Wzorzec Observer

 Observer obserwuje stan innego obiektu (w tym przypadku AccountStorage - klasy pełniącej niejako rolę Cache'a rejestracji). W momencie utworzeniego nowego konta
 observer jest powiadamiany i wykonuje swoją logikę - w tym przypadku informyjną wiadomość z zarejestrowanym użytkownikiem w konsoli.

Koniec, Tydzień 5, Wzorzec Observer */

@Slf4j
@Component
public class NewAccountNotifier implements NewAccountListener{

    @Override
    public void onAccountCreated(AccountEntity account) {
        log.info("New account with nickname '{}' created!", account.getUsername());
    }
}

