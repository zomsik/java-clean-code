package pollub.projekt.ddd.post.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pollub.projekt.ddd.post.domain.PostLike;
import pollub.projekt.ddd.post.domain.PostLikesRepository;

@Repository
@Primary
@AllArgsConstructor
public class PostLikesAdapter implements PostLikesRepository {

    private final PostLikesJpaRepository postLikesJpaRepository;


    @Override
    public PostLike save(PostLike postLike) {
        return postLikesJpaRepository.save(postLike.translate()).translate();
    }

    @Override
    public Integer getLikesOfPost(Integer postId) {
        return postLikesJpaRepository.getLikesOfPost(postId);
    }

    @Override
    public void deleteByPostIdAndAccountId(Integer postId, Integer accountId) {
        postLikesJpaRepository.deleteByPostIdAndAccountId(postId, accountId);
    }
}
