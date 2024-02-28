package pollub.projekt.ddd.comment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.projekt.ddd.comment.rest.dto.CreateCommentRequestDto;
import pollub.projekt.ddd.comment.rest.dto.CreateCommentResponseDto;
import pollub.projekt.ddd.comment.rest.dto.LikeResponseDto;
import pollub.projekt.ddd.common.application.comment.CommentDto;
import pollub.projekt.ddd.common.application.comment.CommentFacade;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentFacadeImpl implements CommentFacade {

    private final GetCommentsService getCommentsService;
    private final SetCommentLikeService setCommentLikeService;
    @Override
    public List<CommentDto> getComments(Integer postId, String jwt) {
        return getCommentsService.getComments(postId, jwt);
    }

    @Override
    public LikeResponseDto likeComment(Integer commentId, String jwt) {
        return setCommentLikeService.likeComment(commentId, jwt);
    }

    @Override
    public LikeResponseDto dislikeComment(Integer commentId, String jwt) {
        return setCommentLikeService.dislikeComment(commentId, jwt);
    }

    @Override
    public CreateCommentResponseDto createComment(CreateCommentRequestDto createCommentRequestDto, String jwt) {
        return setCommentLikeService.createComment(createCommentRequestDto, jwt);
    }

}
