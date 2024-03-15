package pollub.projekt.ddd.post.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pollub.projekt.ddd.common.application.post.PostDto;
import pollub.projekt.ddd.common.application.post.PostFacade;
import pollub.projekt.ddd.post.domain.exception.PostErrorResponseBody;
import pollub.projekt.ddd.post.domain.exception.PostException;
import pollub.projekt.ddd.post.rest.dto.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/posts")
@RestController
@Slf4j
@Valid
@CrossOrigin
public class PostController {

    private final PostFacade postFacade;


    @ExceptionHandler(PostException.class)
    public ResponseEntity<PostErrorResponseBody> handlePostsException(PostException ex) {
        log.error(ex.getMessage());
        return PostErrorResponse.fromPostException(ex).asResponseEntity();
    }


    @GetMapping(path = "/get-posts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<PostDto>> getPosts(@RequestParam(value = "category", required=false) String category,
                                                  @RequestParam Integer page,
                                                  @RequestParam Integer postsPerPage,
                                                  @RequestHeader(value = "jwt", required = false) String jwt) {

        List<PostDto> postList = postFacade.getPosts(category, page, postsPerPage, jwt);
        return ResponseEntity.ok(postList);
    }

    @GetMapping(path = "/get-post/{postId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<PostDto> getPosts(@PathVariable(value="postId") Integer postId,
                                            @RequestHeader(value = "jwt", required = false) String jwt) {
        PostDto post = postFacade.getPostById(postId, jwt);
        return ResponseEntity.ok(post);
    }

    @GetMapping(path = "/get-post/image/{postId}")
    public ResponseEntity<byte[]> getPostImage(@PathVariable(value="postId") Integer postId) {
        byte[] image = postFacade.getImageByPostId(postId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);

        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @GetMapping(path = "/count-posts")
    public ResponseEntity<Integer> countPosts(@RequestParam(value = "category", required=false) String category) {

        Integer postsCount = postFacade.countPosts(category);


        return ResponseEntity.ok(postsCount);
    }


    @PostMapping(path = "/create-post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePostResponseDto> createPost(@RequestBody @Valid CreatePostRequestDto createPostRequestDto,
                                                            @RequestHeader(value = "jwt") String jwt) {


        return ResponseEntity.ok(postFacade.createPost(createPostRequestDto, jwt));
    }

    @PatchMapping(path = "/update-post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdatePostResponseDto> createPost(@RequestBody @Valid UpdatePostRequestDto updatePostRequestDto,
                                                            @RequestHeader(value = "jwt") String jwt) {


        return ResponseEntity.ok(postFacade.updatePost(updatePostRequestDto, jwt));
    }

    @PatchMapping(path = "/restore-post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestorePostResponseDto> restorePost(@RequestBody @Valid RestorePostRequestDto restorePostRequestDto,
                                                            @RequestHeader(value = "jwt") String jwt) {


        return ResponseEntity.ok(postFacade.restorePost(restorePostRequestDto, jwt));
    }


    @PostMapping(path = "/like/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LikeResponseDto> likeComment(@PathVariable(value="postId") Integer postId,
                                                       @RequestHeader(value = "jwt") String jwt) {

        return ResponseEntity.ok(postFacade.likePost(postId, jwt));
    }

    @PostMapping(path = "/dislike/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LikeResponseDto> dislikeComment(@PathVariable(value="postId") Integer postId,
                                                          @RequestHeader(value = "jwt") String jwt) {

        return ResponseEntity.ok(postFacade.dislikePost(postId, jwt));
    }



}
