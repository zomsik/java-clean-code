package pollub.projekt.ddd.exception.handling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class AppExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppErrorResponseBody> handleGeneralException(Exception e) {
        log.error(e.getMessage());
        return new AppErrorResponse(AppCommonErrorCodes.GENERAL_APP_ERROR).asResponseEntity();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<AppErrorResponseBody> handleWrongMethodException(Exception e) {
        log.error(e.getMessage());
        return new AppErrorResponse(AppCommonErrorCodes.WRONG_METHOD_ERROR).asResponseEntity();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorResponseBody> handleWrongRequestException(Exception e) {
        log.error(e.getMessage());
        return new AppErrorResponse(AppCommonErrorCodes.WRONG_REQUEST_ERROR).asResponseEntity();
    }

}
