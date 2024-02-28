package pollub.projekt.ddd.account.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import pollub.projekt.ddd.account.domain.exception.AccountErrorResponseBody;
import pollub.projekt.ddd.account.domain.exception.AccountException;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AccountErrorResponse implements AccountErrorResponseBody {

    private final String message;
    private final HttpStatusCode httpStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime exceptionTime;

    private AccountErrorResponse(String message, HttpStatus status) {
        this.httpStatus = status;
        this.message = message;
        this.exceptionTime = LocalDateTime.now();
    }

    public static AccountErrorResponse fromAccountException(AccountException ex) {
        return new AccountErrorResponse(ex.getMessage(), ex.getStatusCode());
    }

}
