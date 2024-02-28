package pollub.projekt.ddd.post.domain.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface PostErrorResponseBody {

    String getMessage();
    HttpStatusCode getHttpStatus();
    LocalDateTime getExceptionTime();

    default ResponseEntity<PostErrorResponseBody> asResponseEntity(){
        return ResponseEntity.status(getHttpStatus()).body(this);
    }

}
