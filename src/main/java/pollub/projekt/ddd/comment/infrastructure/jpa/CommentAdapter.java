package pollub.projekt.ddd.comment.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pollub.projekt.ddd.comment.domain.Comment;
import pollub.projekt.ddd.comment.domain.CommentRepository;

import java.util.List;

@Repository
@Primary
@AllArgsConstructor
public class CommentAdapter implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public List<Comment> getComments(Integer postId) {
        return commentJpaRepository.getByPostId(postId).stream().map(CommentEntity::translate).toList();
    }

    @Override
    public boolean isCommentLikedByUser(Integer id, Integer accountId) {
        return commentJpaRepository.isCommentLikedByUser(id, accountId);
    }

    @Override
    public Comment save(Comment c) {
        return commentJpaRepository.save(c.translate()).translate();
    }
}
