package pollub.projekt.ddd.post.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PostException extends RuntimeException {

    private final HttpStatus statusCode;

    public PostException(PostErrorCodes error) {
        super(error.getErrorMessage());
        this.statusCode = error.getResponseCode();
    }

    public PostException(PostErrorCodes error, String ... additionalErrorMessage) {
        super(String.format(error.getErrorMessage(), additionalErrorMessage));
        this.statusCode = error.getResponseCode();
    }

}
