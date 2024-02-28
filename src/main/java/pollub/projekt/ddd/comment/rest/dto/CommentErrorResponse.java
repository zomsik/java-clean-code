package pollub.projekt.ddd.comment.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import pollub.projekt.ddd.comment.domain.exception.CommentErrorResponseBody;
import pollub.projekt.ddd.comment.domain.exception.CommentException;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentErrorResponse implements CommentErrorResponseBody {


    private final String message;
    private final HttpStatusCode httpStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime exceptionTime;

    private CommentErrorResponse(String message, HttpStatus status) {
        this.httpStatus = status;
        this.message = message;
        this.exceptionTime = LocalDateTime.now();
    }

    public static CommentErrorResponse fromCommentException(CommentException ex){
        return new CommentErrorResponse(ex.getMessage(), ex.getStatusCode());
    }


}
