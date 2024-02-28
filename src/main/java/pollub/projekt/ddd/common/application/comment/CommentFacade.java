package pollub.projekt.ddd.common.application.comment;

import pollub.projekt.ddd.comment.rest.dto.CreateCommentRequestDto;
import pollub.projekt.ddd.comment.rest.dto.CreateCommentResponseDto;
import pollub.projekt.ddd.comment.rest.dto.LikeResponseDto;

import java.util.List;

public interface CommentFacade {
    List<CommentDto> getComments(Integer postId, String jwt);

    LikeResponseDto likeComment(Integer commentId, String jwt);

    LikeResponseDto dislikeComment(Integer commentId, String jwt);

    CreateCommentResponseDto createComment(CreateCommentRequestDto createCommentRequestDto, String jwt);

}
