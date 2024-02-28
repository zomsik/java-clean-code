package pollub.projekt.ddd.common.application.post;

import pollub.projekt.ddd.post.rest.dto.CreatePostRequestDto;
import pollub.projekt.ddd.post.rest.dto.CreatePostResponseDto;
import pollub.projekt.ddd.post.rest.dto.LikeResponseDto;

import java.util.List;

public interface PostFacade {

    List<PostDto> getPosts(String category, Integer page, Integer postsPerPage, String jwt);

    PostDto getPostById(Integer postId, String jwt);

    byte[] getImageByPostId(Integer postId);

    Integer countPosts(String category);

    LikeResponseDto likePost(Integer postId, String jwt);

    LikeResponseDto dislikePost(Integer postId, String jwt);

    CreatePostResponseDto createPost(CreatePostRequestDto createPostRequestDto, String jwt);
}
