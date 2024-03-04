package pollub.projekt.ddd.post.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pollub.projekt.ddd.post.domain.Post;
import pollub.projekt.ddd.post.domain.PostRepository;

import java.util.List;
import java.util.Optional;



    /* Tydzień 3, Wzorzec Adapter

        Wzorzec Adapter pozwala w prosty sposób na adaptację części domenowej z częścia bazodanową projektu. W adapterze zachodzą
        głównie translacje obiektów jednej klasy na drugą i spowrotem przy zapisie i odczycie. Spełnia on rolę łącznika.

    Koniec, Tydzień 3, Wzorzec Adapter */

@Repository
@Primary
@AllArgsConstructor
public class PostAdapter implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Optional<Post> getById(Integer id) {
        return postJpaRepository.getByPostId(id).map(PostEntity::translate);
    }

    @Override
    public List<Post> getByCategoryAndPageAndPostsOnPage(String category, Integer page, Integer postsOnPage) {
        return postJpaRepository
                .getByCategoryAndOffsetAndPostsOnPage(category, (page - 1) * postsOnPage, postsOnPage)
                .stream()
                .map(PostEntity::translate)
                .toList();
    }

    @Override
    public List<Post> getByPageAndPostsOnPage(Integer page, Integer postsOnPage) {
        return postJpaRepository
                .getByOffsetAndPostsOnPage((page - 1) * postsOnPage, postsOnPage)
                .stream()
                .map(PostEntity::translate)
                .toList();
    }

    @Override
    public byte[] getImageByPostId(Integer postId) {
        return postJpaRepository.getImageByPostId(postId);
    }

    @Override
    public Integer countPosts() {
        return postJpaRepository.countPosts();
    }

    @Override
    public Integer countPostsByCategory(String category) {
        return postJpaRepository.countPostsInCategory(category);
    }

    @Override
    public boolean isPostLikedByUser(Integer id, Integer accountId) {
        return postJpaRepository.isPostLikedByUser(id, accountId);
    }

    @Override
    public Post save(Post p) {
        return postJpaRepository.save(p.translate()).translate();
    }
}
