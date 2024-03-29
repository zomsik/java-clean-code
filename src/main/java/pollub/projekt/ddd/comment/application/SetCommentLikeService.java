package pollub.projekt.ddd.comment.application;

import org.springframework.stereotype.Service;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.comment.domain.Comment;
import pollub.projekt.ddd.comment.domain.CommentLike;
import pollub.projekt.ddd.comment.domain.CommentLikesRepository;
import pollub.projekt.ddd.comment.domain.CommentRepository;
import pollub.projekt.ddd.comment.domain.exception.CommentErrorCodes;
import pollub.projekt.ddd.comment.domain.exception.CommentException;
import pollub.projekt.ddd.comment.rest.dto.LikeResponseDto;
import pollub.projekt.ddd.common.application.account.AccountFacade;
import pollub.projekt.ddd.common.application.time.TimeProvider;
import pollub.projekt.ddd.common.utils.JwtUtil;

@Service
public class SetCommentLikeService extends SetCommentService {

    private final CommentRepository commentRepository;
    private final CommentLikesRepository commentLikesRepository;
    private final AccountFacade accountFacade;
    private final JwtUtil jwtUtil;
    private final TimeProvider timeProvider;

    public SetCommentLikeService(CommentRepository commentRepository, CommentLikesRepository commentLikesRepository,
                                 AccountFacade accountFacade, TimeProvider timeProvider) {
        this.commentRepository = commentRepository;
        this.commentLikesRepository = commentLikesRepository;
        this.accountFacade = accountFacade;
        this.jwtUtil = JwtUtil.getInstance();
        this.timeProvider = timeProvider;
    }


    public LikeResponseDto changeLikeComment(Integer commentId, String jwt) {
        if (jwtUtil.valid(jwt)) {
            String user = jwtUtil.getUser(jwt);
            Integer accountId = accountFacade.getIdByLogin(user);

            if (commentRepository.isCommentLikedByUser(commentId, accountId)) {
                throw new CommentException(CommentErrorCodes.ALREADY_LIKED);
            }

            /* Tydzień 2, Wzorzec Builder

                Builder upraszcza tworzenie obiektów danej klasy, możemy w kolejnych setterach ustawiać po kolei
                kolejne pola danej klasy, nie wymagany jest dzięki temu wieloargumentowy konstruktor,
                zwłaszcza gdy nie chcemy ustawiać wszystkich pól.

            Koniec, Tydzień 2, Wzorzec Builder */
            CommentLike commentLike = new CommentLike.CommentLikeBuilder()
                    .setComment(new Comment(commentId))
                    .setAccount(new Account(accountId))
                    .setCreateDate(timeProvider.currentDateTime())
                    .build();


            commentLike = commentLikesRepository.save(commentLike);

            if (commentLike.getId() != null ) {

                Integer likes = commentLikesRepository.getLikesOfComment(commentId);

                return LikeResponseDto.builder()
                        .success(true)
                        .likes(likes)
                        .build();
            } else {
                throw new CommentException(CommentErrorCodes.LIKE_ERROR);
            }

        } else {
            throw new CommentException(CommentErrorCodes.SESSION_EXPIRED);
        }
    }


}
