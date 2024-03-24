package pollub.projekt.ddd.account.domain;


/* Tydzień 6, Wzorzec State

Zastosowanie wzorca State umożliwiło każdej roli posiadanie własnej klasy z unikalnymi implementacjami metod,
 takich jak logAction(), które mogą być inaczej realizowane w zależności od roli. Na przykład, akcje podjęte
  przez użytkownika mogą być logowane inaczej niż akcje moderatora lub administratora. To zróżnicowanie jest teraz
  osiągalne dzięki definiowaniu oddzielnych klas dla każdej roli, co pozwala na większą modularność i łatwość w zarządzaniu kodem

Koniec, Tydzień 6, Wzorzec State */
public interface RoleState {
    String getId();
    String getName();
    void logAction(String action);
}
