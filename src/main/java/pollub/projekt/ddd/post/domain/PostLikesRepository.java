package pollub.projekt.ddd.post.domain;


public interface PostLikesRepository {
    PostLike save(PostLike postLike);

    Integer getLikesOfPost(Integer postId);


    void deleteByPostIdAndAccountId(Integer postId, Integer accountId);
}
