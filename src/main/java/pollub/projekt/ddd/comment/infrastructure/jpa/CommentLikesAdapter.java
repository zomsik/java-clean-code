package pollub.projekt.ddd.comment.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pollub.projekt.ddd.comment.domain.CommentLike;
import pollub.projekt.ddd.comment.domain.CommentLikesRepository;

@Repository
@Primary
@AllArgsConstructor
public class CommentLikesAdapter implements CommentLikesRepository {

    private final CommentLikesJpaRepository commentLikesJpaRepository;


    @Override
    public CommentLike save(CommentLike commentLike) {
        return commentLikesJpaRepository.save(commentLike.translate()).translate();
    }

    @Override
    public Integer getLikesOfComment(Integer commentId) {
        return commentLikesJpaRepository.getLikesOfComment(commentId);
    }

    @Override
    public void deleteByCommentIdAndAccountId(Integer commentId, Integer accountId) {
        commentLikesJpaRepository.deleteByCommentIdAndAccountId(commentId, accountId);
    }
}
