package pollub.projekt.ddd.account.domain.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface AccountErrorResponseBody {

    String getMessage();
    HttpStatusCode getHttpStatus();
    LocalDateTime getExceptionTime();

    default ResponseEntity<AccountErrorResponseBody> asResponseEntity(){
        return ResponseEntity.status(getHttpStatus()).body(this);
    }

}
