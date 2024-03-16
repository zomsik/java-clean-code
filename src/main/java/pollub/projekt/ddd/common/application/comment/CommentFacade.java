package pollub.projekt.ddd.common.application.comment;

import pollub.projekt.ddd.comment.rest.dto.CreateCommentRequestDto;
import pollub.projekt.ddd.comment.rest.dto.CreateCommentResponseDto;
import pollub.projekt.ddd.comment.rest.dto.LikeResponseDto;

import java.util.List;

public interface CommentFacade {
    List<CommentDto> getComments(Integer postId, String jwt);

    LikeResponseDto changeLikeComment(Integer commentId, String jwt, boolean like);


    CreateCommentResponseDto createComment(CreateCommentRequestDto createCommentRequestDto, String jwt);

}
