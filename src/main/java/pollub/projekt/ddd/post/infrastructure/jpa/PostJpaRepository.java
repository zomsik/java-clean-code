package pollub.projekt.ddd.post.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostJpaRepository extends JpaRepository<PostEntity, Integer> {

    @Query(value = """
            SELECT * FROM post p
            ORDER BY p.id DESC
            OFFSET :offsetNumber
            FETCH NEXT :postsOnPage ROWS ONLY
            """, nativeQuery = true)
    List<PostEntity> getByOffsetAndPostsOnPage(@Param("offsetNumber") Integer offsetNumber,
                                             @Param("postsOnPage")  Integer postsOnPage);

    @Query(value = """
            SELECT p FROM PostEntity p
            WHERE p.id = :id
            """)
    Optional<PostEntity> getByPostId(@Param("id") Integer id);

    @Query(value = """
            SELECT p.image FROM PostEntity p
            WHERE p.id = :id
            """)
    byte[] getImageByPostId(@Param("id") Integer id);

    @Query(value = """
            SELECT p.* FROM post p
            JOIN category c on c.id = p.category_id
            WHERE c.name = :category
            ORDER BY p.id DESC
            OFFSET :offsetNumber
            FETCH NEXT :postsOnPage ROWS ONLY
            """, nativeQuery = true)
    List<PostEntity> getByCategoryAndOffsetAndPostsOnPage(@Param("category") String category,
                                                        @Param("offsetNumber") Integer offsetNumber,
                                                        @Param("postsOnPage")  Integer postsOnPage);

    @Query(value = """
            SELECT count(p) FROM PostEntity p
            """)
    Integer countPosts();

    @Query(value = """
            SELECT count(p) FROM post p
            JOIN category c on c.id = p.category_id
            WHERE c.name = :category
            """, nativeQuery = true)
    Integer countPostsInCategory(@Param("category") String category);

    @Query(value = """
    SELECT CASE
    WHEN COUNT(p) > 0 THEN true ELSE false END
    FROM post p
    JOIN post_likes pl on p.id = pl.post_id
    WHERE p.id = :id AND pl.account_id = :accountId
    """, nativeQuery = true)
    boolean isPostLikedByUser(@Param("id") Integer id,
                                 @Param("accountId") Integer accountId);

}
