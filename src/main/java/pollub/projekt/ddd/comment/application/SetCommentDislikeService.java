package pollub.projekt.ddd.comment.application;

import org.springframework.stereotype.Service;
import pollub.projekt.ddd.comment.domain.CommentLikesRepository;
import pollub.projekt.ddd.comment.domain.CommentRepository;
import pollub.projekt.ddd.comment.domain.exception.CommentErrorCodes;
import pollub.projekt.ddd.comment.domain.exception.CommentException;
import pollub.projekt.ddd.comment.rest.dto.LikeResponseDto;
import pollub.projekt.ddd.common.application.account.AccountFacade;
import pollub.projekt.ddd.common.utils.JwtUtil;

@Service
public class SetCommentDislikeService extends SetCommentService {

    private final CommentRepository commentRepository;
    private final CommentLikesRepository commentLikesRepository;
    private final AccountFacade accountFacade;
    private final JwtUtil jwtUtil;

    public SetCommentDislikeService(CommentRepository commentRepository, CommentLikesRepository commentLikesRepository,
                                    AccountFacade accountFacade) {
        this.commentRepository = commentRepository;
        this.commentLikesRepository = commentLikesRepository;
        this.accountFacade = accountFacade;
        this.jwtUtil = JwtUtil.getInstance();
    }


    public LikeResponseDto changeLikeComment(Integer commentId, String jwt) {
        if (jwtUtil.valid(jwt)) {
            String user = jwtUtil.getUser(jwt);
            Integer accountId = accountFacade.getIdByLogin(user);


            if (!commentRepository.isCommentLikedByUser(commentId, accountId)) {
                throw new CommentException(CommentErrorCodes.ALREADY_DISLIKED);
            }

            commentLikesRepository.deleteByCommentIdAndAccountId(commentId, accountId);

            if (!commentRepository.isCommentLikedByUser(commentId, accountId)) {

                Integer likes = commentLikesRepository.getLikesOfComment(commentId);

                return LikeResponseDto.builder()
                        .success(true)
                        .likes(likes)
                        .build();
            } else {
                throw new CommentException(CommentErrorCodes.DISLIKE_ERROR);
            }

        } else {
            throw new CommentException(CommentErrorCodes.SESSION_EXPIRED);
        }
    }


}
