package pollub.projekt.ddd.exception.handling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
public class AppErrorResponse implements AppErrorResponseBody{

    private final String message;
    private final HttpStatusCode httpStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime exceptionTime;

    public AppErrorResponse(AppCommonErrorCodes errorCode, String ... args) {
        this.message = errorCode.getErrorMessage().formatted(args);
        this.httpStatus = errorCode.getResponseCode();
        this.exceptionTime = LocalDateTime.now();
    }

}
