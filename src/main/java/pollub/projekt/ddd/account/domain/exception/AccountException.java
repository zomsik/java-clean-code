package pollub.projekt.ddd.account.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AccountException extends RuntimeException {

    private final HttpStatus statusCode;

    public AccountException(AccountErrorCodes error) {
        super(error.getErrorMessage());
        this.statusCode = error.getResponseCode();
    }

    public AccountException(AccountErrorCodes error, String ... additionalErrorMessage) {
        super(String.format(error.getErrorMessage(), additionalErrorMessage));
        this.statusCode = error.getResponseCode();
    }

}
