package pollub.projekt.ddd.comment.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentJpaRepository  extends JpaRepository<CommentEntity, Integer> {


    @Query(value = """
            SELECT c FROM CommentEntity c
            WHERE c.post.id = :postId
            """)
    List<CommentEntity> getByPostId(@Param("postId") Integer postId);

    @Query(value = """
    SELECT CASE
    WHEN COUNT(c) > 0 THEN true ELSE false END
    FROM comment c
    JOIN comment_likes cl on c.id = cl.comment_id
    WHERE c.id = :id AND cl.account_id = :accountId
    """, nativeQuery = true)
    boolean isCommentLikedByUser(@Param("id") Integer id,
                                 @Param("accountId") Integer accountId);
}
