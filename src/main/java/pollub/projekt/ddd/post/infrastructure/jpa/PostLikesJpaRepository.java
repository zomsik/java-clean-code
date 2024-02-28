package pollub.projekt.ddd.post.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PostLikesJpaRepository extends JpaRepository<PostLikesEntity, Integer> {

    @Query(value = """
            SELECT COUNT(pl) FROM PostLikesEntity pl
            WHERE pl.post.id = :postId
            """)
    Integer getLikesOfPost(@Param("postId") Integer postId);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM PostLikesEntity pl
        WHERE pl.post.id = :postId
        AND pl.account.id = :accountId
        """)
    void deleteByPostIdAndAccountId(@Param("postId") Integer postId,
                                    @Param("accountId") Integer accountId);

}