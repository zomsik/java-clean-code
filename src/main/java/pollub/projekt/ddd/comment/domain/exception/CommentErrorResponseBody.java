package pollub.projekt.ddd.comment.domain.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface CommentErrorResponseBody {

    String getMessage();
    HttpStatusCode getHttpStatus();
    LocalDateTime getExceptionTime();

    default ResponseEntity<CommentErrorResponseBody> asResponseEntity(){
        return ResponseEntity.status(getHttpStatus()).body(this);
    }

}
