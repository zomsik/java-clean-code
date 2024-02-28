package pollub.projekt.ddd.comment.domain;

import java.util.List;

public interface CommentRepository {
    List<Comment> getComments(Integer postId);

    boolean isCommentLikedByUser(Integer id, Integer accountId);

    Comment save(Comment c);
}
