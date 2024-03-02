package pollub.projekt.ddd.post.application;

import org.springframework.stereotype.Service;
import pollub.projekt.ddd.common.application.account.AccountFacade;
import pollub.projekt.ddd.common.application.post.PostDto;
import pollub.projekt.ddd.common.utils.JwtUtil;
import pollub.projekt.ddd.post.domain.CategoryEnum;
import pollub.projekt.ddd.post.domain.Post;
import pollub.projekt.ddd.post.domain.PostRepository;
import pollub.projekt.ddd.post.domain.exception.PostErrorCodes;
import pollub.projekt.ddd.post.domain.exception.PostException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class GetPostsService {

    private final AccountFacade accountFacade;
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;

    public GetPostsService(AccountFacade accountFacade, PostRepository postRepository) {
        this.accountFacade = accountFacade;
        this.postRepository = postRepository;
        this.jwtUtil = JwtUtil.getInstance();
    }

    public List<PostDto> getPosts(String category, Integer page, Integer postsPerPage, String jwt) {

        if (page == null || postsPerPage == null)
            throw new PostException(PostErrorCodes.NO_FILTER);

        List<PostDto> postList;

        if (category != null && !category.equals("")) {
            postList = postRepository
                    .getByCategoryAndPageAndPostsOnPage(category, page, postsPerPage)
                    .stream()
                    .map(Post::translateToDto)
                    .toList();
        } else {
            postList =  postRepository
                    .getByPageAndPostsOnPage(page, postsPerPage)
                    .stream()
                    .map(Post::translateToDto)
                    .toList();
        }


        if (jwtUtil.valid(jwt)) {
            String user = jwtUtil.getUser(jwt);
            Integer accountId = accountFacade.getIdByLogin(user);

            for (PostDto post : postList) {
                if (postRepository.isPostLikedByUser(post.getId(), accountId))
                    post.setLiked(true);
            }

        }

        return postList;
    }

    public PostDto getPostById(Integer postId, String jwt) {
        if (postId == null) {
            return null;
        }

        PostDto post = postRepository.getById(postId).map(Post::translateToDto).orElse(null);

        if (post != null) {
            if (jwtUtil.valid(jwt)) {
                String user = jwtUtil.getUser(jwt);
                Integer accountId = accountFacade.getIdByLogin(user);

                if (postRepository.isPostLikedByUser(post.getId(), accountId))
                    post.setLiked(true);


            }
        }

        return post;
    }

    public byte[] getImageByPostId(Integer postId) {
        if (postId == null || postId <= 0)
            throw new PostException(PostErrorCodes.WRONG_POST_ID);

        byte[] bytes = postRepository.getImageByPostId(postId);

        if (bytes == null) {
            throw new PostException(PostErrorCodes.IMAGE_NOT_FOUND);
        }

        return bytes;
    }



    public Integer countPosts(String category) {
        if (Objects.equals(category, CategoryEnum.NONE.name)) {
            return postRepository.countPosts();
        } else if (Arrays.stream(CategoryEnum.values()).anyMatch(cat -> cat.name().equalsIgnoreCase(category)) ){
            return postRepository.countPostsByCategory(category);
        } else {
            return postRepository.countPosts();
        }

    }
}
