package pollub.projekt.ddd.exception.handling;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface AppErrorResponseBody {

    String getMessage();
    HttpStatusCode getHttpStatus();
    LocalDateTime getExceptionTime();

    default ResponseEntity<AppErrorResponseBody> asResponseEntity(){
        return ResponseEntity.status(getHttpStatus()).body(this);
    }
}
