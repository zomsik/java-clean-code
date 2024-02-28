package pollub.projekt.ddd.account.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum AccountErrorCodes {

    USER_NOT_FOUND("Nie znaleziono użytkownika o takim loginie i haśle", HttpStatus.NOT_FOUND),
    WRONG_PASSWORD("Niepoprawne hasło", HttpStatus.UNAUTHORIZED),
    USERNAME_TAKEN("Nazwa użytkownika zajęta", HttpStatus.CONFLICT),
    REGISTER_ERROR("Błąd w trakcie rejestracji", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String errorMessage;
    private final HttpStatus responseCode;

}
