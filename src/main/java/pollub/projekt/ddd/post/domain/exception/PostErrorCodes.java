package pollub.projekt.ddd.post.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum PostErrorCodes {

    NO_FILTER("Bledny filter dla postow!", HttpStatus.BAD_REQUEST),
    WRONG_POST_ID("Niepoprawny identyfikator postu", HttpStatus.BAD_REQUEST),
    NOT_FOUND("Nie znaleziono postu dla takiego identyfikatora", HttpStatus.NOT_FOUND),
    IMAGE_NOT_FOUND("Nie znaleziono obrazka dla postu", HttpStatus.NOT_FOUND),
    SESSION_EXPIRED("Sesja wygasła", HttpStatus.BAD_REQUEST),
    LIKE_ERROR("Błąd w trakcie przetwarzania like'a", HttpStatus.INTERNAL_SERVER_ERROR),
    DISLIKE_ERROR("Błąd w trakcie przetwarzania dislike'a", HttpStatus.INTERNAL_SERVER_ERROR),
    ALREADY_LIKED("Komentarz już polubiony", HttpStatus.INTERNAL_SERVER_ERROR ),
    ALREADY_DISLIKED("Komentarz już odlubiony", HttpStatus.INTERNAL_SERVER_ERROR ),
    FIELDS_NOT_FILLED("Brak wypełnienia obowiązkowych pól", HttpStatus.BAD_REQUEST),
    CREATE_POST_ERROR("Błąd tworzenia postu", HttpStatus.BAD_REQUEST),
    INVALID_ACCOUNT_ERROR("Brak uprawnień do edycji postu", HttpStatus.FORBIDDEN),
    RESTORE_POINT_NOT_FOUNT("Nie znaleziono poprzedniej wersji posta", HttpStatus.NOT_FOUND);

    private final String errorMessage;
    private final HttpStatus responseCode;

}
