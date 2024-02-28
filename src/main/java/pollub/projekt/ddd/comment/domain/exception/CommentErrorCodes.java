package pollub.projekt.ddd.comment.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum CommentErrorCodes {

    NO_FILTER("Bledny filter dla postow!", HttpStatus.BAD_REQUEST),
    SESSION_EXPIRED("Sesja wygasła", HttpStatus.BAD_REQUEST),
    LIKE_ERROR("Błąd w trakcie przetwarzania like'a", HttpStatus.INTERNAL_SERVER_ERROR),
    DISLIKE_ERROR("Błąd w trakcie przetwarzania dislike'a", HttpStatus.INTERNAL_SERVER_ERROR),
    ALREADY_LIKED("Komentarz już polubiony", HttpStatus.INTERNAL_SERVER_ERROR ),
    ALREADY_DISLIKED("Komentarz już odlubiony", HttpStatus.INTERNAL_SERVER_ERROR ),
    FIELDS_NOT_FILLED("Brak wypełnienia obowiązkowych pól", HttpStatus.BAD_REQUEST),
    CREATE_COMMENT_ERROR("Błąd tworzenia komentarza", HttpStatus.BAD_REQUEST);

    private final String errorMessage;
    private final HttpStatus responseCode;

}
