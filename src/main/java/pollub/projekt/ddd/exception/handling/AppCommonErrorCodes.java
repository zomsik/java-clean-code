package pollub.projekt.ddd.exception.handling;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
enum AppCommonErrorCodes {

    GENERAL_APP_ERROR("Wystąpił nieoczekiwany błąd!", HttpStatus.INTERNAL_SERVER_ERROR),
    WRONG_METHOD_ERROR("Wybrana metoda nie jest obsługiwana!", HttpStatus.METHOD_NOT_ALLOWED),
    WRONG_REQUEST_ERROR("W requeście nie podano wszystkich wymaganych pól!", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_ERROR("Niepowodzenie w trakcie autoryzacji", HttpStatus.UNAUTHORIZED),
    NO_PRIVILEGES_ERROR("Brak uprawnień do wykonania tej metody!", HttpStatus.FORBIDDEN);

    private final String errorMessage;
    private final HttpStatus responseCode;
}
