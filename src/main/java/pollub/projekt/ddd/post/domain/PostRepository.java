package pollub.projekt.ddd.post.domain;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Optional<Post> getById(Integer id);

    List<Post> getByCategoryAndPageAndPostsOnPage(String category, Integer page, Integer postsOnPage);

    List<Post> getByPageAndPostsOnPage(Integer page, Integer postsOnPage);

    byte[] getImageByPostId(Integer postId);

    Integer countPosts();

    Integer countPostsByCategory(String category);

    boolean isPostLikedByUser(Integer id, Integer accountId);

    Post save(Post p);
}
