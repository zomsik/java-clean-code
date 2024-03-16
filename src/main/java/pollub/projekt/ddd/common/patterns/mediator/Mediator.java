package pollub.projekt.ddd.common.patterns.mediator;

/* Tydzień 5, Wzorzec Mediator

 Mediator deklaruje metodę notify, która służy do informowania o różnych zdarzeniach przez inne klasy projektu.
 W implementacji mediatora występuje logika odpowiedzialna za kolor komunikatu w zależności od klasy z której komunikat przyszedł.

Koniec, Tydzień 5, Wzorzec Mediator */

public interface Mediator {
    void notify(Object sender, String message);
}
