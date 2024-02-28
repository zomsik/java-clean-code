package pollub.projekt.ddd.comment.domain;

public interface CommentLikesRepository {
    CommentLike save(CommentLike commentLike);

    Integer getLikesOfComment(Integer commentId);

    void deleteByCommentIdAndAccountId(Integer commentId, Integer accountId);
}
