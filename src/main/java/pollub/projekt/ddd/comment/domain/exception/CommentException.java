package pollub.projekt.ddd.comment.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommentException extends RuntimeException {

    private final HttpStatus statusCode;

    public CommentException(CommentErrorCodes error) {
        super(error.getErrorMessage());
        this.statusCode = error.getResponseCode();
    }

    public CommentException(CommentErrorCodes error, String ... additionalErrorMessage) {
        super(String.format(error.getErrorMessage(), additionalErrorMessage));
        this.statusCode = error.getResponseCode();
    }

}
