package pollub.projekt.ddd.comment.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CommentLikesJpaRepository extends JpaRepository<CommentLikesEntity, Integer> {

    @Query(value = """
            SELECT COUNT(cl) FROM CommentLikesEntity cl
            WHERE cl.comment.id = :commentId
            """)
    Integer getLikesOfComment(@Param("commentId") Integer commentId);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM CommentLikesEntity cl
        WHERE cl.comment.id = :commentId
        AND cl.account.id = :accountId
        """)
    void deleteByCommentIdAndAccountId(@Param("commentId") Integer commentId,
                                       @Param("accountId") Integer accountId);
}