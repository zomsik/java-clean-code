package pollub.projekt.ddd.post.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.projekt.ddd.common.application.post.PostDto;
import pollub.projekt.ddd.common.application.post.PostFacade;
import pollub.projekt.ddd.post.rest.dto.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostFacadeImpl implements PostFacade {

    private final GetPostsService getPostsService;
    private final SetPostsService setPostsService;

    @Override
    public List<PostDto> getPosts(String category, Integer page, Integer postsPerPage, String jwt) {
        return getPostsService.getPosts(category, page, postsPerPage, jwt);
    }

    @Override
    public PostDto getPostById(Integer postId, String jwt) {
        return getPostsService.getPostById(postId, jwt);
    }

    @Override
    public byte[] getImageByPostId(Integer postId) {
        return getPostsService.getImageByPostId(postId);
    }

    @Override
    public Integer countPosts(String category) {
        return getPostsService.countPosts(category);
    }

    @Override
    public LikeResponseDto likePost(Integer postId, String jwt) {
        return setPostsService.likePost(postId, jwt);
    }

    @Override
    public LikeResponseDto dislikePost(Integer postId, String jwt) {
        return setPostsService.dislikePost(postId, jwt);
    }

    @Override
    public CreatePostResponseDto createPost(CreatePostRequestDto createPostRequestDto, String jwt) {
        return setPostsService.createPost(createPostRequestDto, jwt);
    }

    @Override
    public UpdatePostResponseDto updatePost(UpdatePostRequestDto updatePostRequestDto, String jwt) {
        return setPostsService.updatePost(updatePostRequestDto, jwt);
    }

    @Override
    public RestorePostResponseDto restorePost(RestorePostRequestDto restorePostRequestDto, String jwt) {
        return setPostsService.restorePost(restorePostRequestDto, jwt);
    }
}
