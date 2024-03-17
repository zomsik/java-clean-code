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

    private final SetCommentDislikeService setCommentDislikeService;

    private final CreateCommentService createCommentService;

    @Override
    public List<CommentDto> getComments(Integer postId, String jwt) {
        return getCommentsService.getComments(postId, jwt);
    }

    @Override
    public LikeResponseDto changeLikeComment(Integer commentId, String jwt, boolean like) {

        /* Tydzień 6, Wzorzec Strategy
            Wzorzec Strategy umożliwia zdefiniowanie zachowania obiektu w zależności od potrzeby. Dwie klasy o innym zachowaniu
        dziedziczą po tej samej klasie abstrakcyjnej, a następnie w zależności od wymaganego zachowania, używana jest ta sama metoda,
        ale z innej klasy dziedziczącej (z odpowiedniej strategii).

        Koniec, Tydzień 6, Wzorzec Strategy */
        SetCommentService commentLikeStrategy;
        if (like) {
            commentLikeStrategy = setCommentLikeService;
        } else {
            commentLikeStrategy = setCommentDislikeService;
        }
        return commentLikeStrategy.changeLikeComment(commentId, jwt);
    }

    @Override
    public CreateCommentResponseDto createComment(CreateCommentRequestDto createCommentRequestDto, String jwt) {
        return createCommentService.createComment(createCommentRequestDto, jwt);
    }

}
