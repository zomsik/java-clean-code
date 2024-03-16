package pollub.projekt.ddd.comment.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pollub.projekt.ddd.comment.domain.exception.CommentErrorResponseBody;
import pollub.projekt.ddd.comment.domain.exception.CommentException;
import pollub.projekt.ddd.comment.rest.dto.CommentErrorResponse;
import pollub.projekt.ddd.comment.rest.dto.CreateCommentRequestDto;
import pollub.projekt.ddd.comment.rest.dto.CreateCommentResponseDto;
import pollub.projekt.ddd.comment.rest.dto.LikeResponseDto;
import pollub.projekt.ddd.common.application.comment.CommentDto;
import pollub.projekt.ddd.common.application.comment.CommentFacade;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/comments")
@RestController
@Slf4j
@Valid
@CrossOrigin
public class CommentController {

    private final CommentFacade commentFacade;

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<CommentErrorResponseBody> handleCommentException(CommentException ex) {
        log.error(ex.getMessage());
        return CommentErrorResponse.fromCommentException(ex).asResponseEntity();
    }

    @GetMapping(path = "/post/get-comments/{postId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable(value="postId") Integer postId,
                                                        @RequestHeader(value = "jwt", required = false) String jwt) {
        List<CommentDto> commentList = commentFacade.getComments(postId, jwt);
        return ResponseEntity.ok(commentList);
    }

    @PostMapping(path = "/create-comment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateCommentResponseDto> createPost(@RequestBody @Valid CreateCommentRequestDto createCommentRequestDto,
                                                               @RequestHeader(value = "jwt") String jwt) {


        return ResponseEntity.ok(commentFacade.createComment(createCommentRequestDto, jwt));
    }


    @PostMapping(path = "/like/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LikeResponseDto> likeComment(@PathVariable(value="commentId") Integer commentId,
                                                 @RequestHeader(value = "jwt") String jwt) {

        return ResponseEntity.ok(commentFacade.changeLikeComment(commentId, jwt, true));
    }

    @PostMapping(path = "/dislike/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LikeResponseDto> dislikeComment(@PathVariable(value="commentId") Integer commentId,
                                                 @RequestHeader(value = "jwt") String jwt) {

        return ResponseEntity.ok(commentFacade.changeLikeComment(commentId, jwt, false));
    }


}
