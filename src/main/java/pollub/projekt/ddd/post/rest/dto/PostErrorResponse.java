package pollub.projekt.ddd.post.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import pollub.projekt.ddd.post.domain.exception.PostErrorResponseBody;
import pollub.projekt.ddd.post.domain.exception.PostException;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostErrorResponse implements PostErrorResponseBody {

    private final String message;
    private final HttpStatusCode httpStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime exceptionTime;

    private PostErrorResponse(String message, HttpStatus status) {
        this.httpStatus = status;
        this.message = message;
        this.exceptionTime = LocalDateTime.now();
    }

    public static PostErrorResponse fromPostException(PostException ex){
        return new PostErrorResponse(ex.getMessage(), ex.getStatusCode());
    }


}
