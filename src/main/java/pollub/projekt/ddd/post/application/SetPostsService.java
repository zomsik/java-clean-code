package pollub.projekt.ddd.post.application;

import org.springframework.stereotype.Service;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.common.application.account.AccountFacade;
import pollub.projekt.ddd.common.application.time.TimeProvider;
import pollub.projekt.ddd.common.utils.JwtUtil;
import pollub.projekt.ddd.post.domain.*;
import pollub.projekt.ddd.post.domain.exception.PostErrorCodes;
import pollub.projekt.ddd.post.domain.exception.PostException;
import pollub.projekt.ddd.post.rest.dto.CreatePostRequestDto;
import pollub.projekt.ddd.post.rest.dto.CreatePostResponseDto;
import pollub.projekt.ddd.post.rest.dto.LikeResponseDto;

@Service
public class SetPostsService {

    private final AccountFacade accountFacade;
    private final PostRepository postRepository;
    private final PostLikesRepository postLikesRepository;
    private final JwtUtil jwtUtil;
    private final TimeProvider timeProvider;

    public SetPostsService(AccountFacade accountFacade, PostRepository postRepository,
                           PostLikesRepository postLikesRepository, TimeProvider timeProvider) {
        this.accountFacade = accountFacade;
        this.postRepository = postRepository;
        this.postLikesRepository = postLikesRepository;
        this.jwtUtil = JwtUtil.getInstance();
        this.timeProvider = timeProvider;
    }

    public LikeResponseDto likePost(Integer postId, String jwt) {
        if (jwtUtil.valid(jwt)) {
            String user = jwtUtil.getUser(jwt);
            Integer accountId = accountFacade.getIdByLogin(user);

            if (postRepository.isPostLikedByUser(postId, accountId)) {
                throw new PostException(PostErrorCodes.ALREADY_LIKED);
            }

            PostLike postLike = PostLike.builder()
                    .post(new Post(postId))
                    .account(new Account(accountId))
                    .createDate(timeProvider.currentDateTime())
                    .build();

            postLike = postLikesRepository.save(postLike);

            if (postLike.getId() != null ) {

                Integer likes = postLikesRepository.getLikesOfPost(postId);

                return LikeResponseDto.builder()
                        .success(true)
                        .likes(likes)
                        .build();
            } else {
                throw new PostException(PostErrorCodes.LIKE_ERROR);
            }

        } else {
            throw new PostException(PostErrorCodes.SESSION_EXPIRED);
        }
    }



    public LikeResponseDto dislikePost(Integer postId, String jwt) {
        if (jwtUtil.valid(jwt)) {
            String user = jwtUtil.getUser(jwt);
            Integer accountId = accountFacade.getIdByLogin(user);


            if (!postRepository.isPostLikedByUser(postId, accountId)) {
                throw new PostException(PostErrorCodes.ALREADY_DISLIKED);
            }

            postLikesRepository.deleteByPostIdAndAccountId(postId, accountId);

            if (!postRepository.isPostLikedByUser(postId, accountId)) {

                Integer likes = postLikesRepository.getLikesOfPost(postId);

                return LikeResponseDto.builder()
                        .success(true)
                        .likes(likes)
                        .build();
            } else {
                throw new PostException(PostErrorCodes.DISLIKE_ERROR);
            }

        } else {
            throw new PostException(PostErrorCodes.SESSION_EXPIRED);
        }
    }


    public CreatePostResponseDto createPost(CreatePostRequestDto createPostRequestDto, String jwt) {

        if (jwtUtil.valid(jwt)) {

            if (createPostRequestDto.getText() == null || createPostRequestDto.getText().isEmpty()) {
                throw new PostException(PostErrorCodes.FIELDS_NOT_FILLED);
            }

            if (createPostRequestDto.getImage() == null) {
                createPostRequestDto.setImage(new byte[0]);
            }

            String user = jwtUtil.getUser(jwt);
            Integer accountId = accountFacade.getIdByLogin(user);
            Integer categoryId = CategoryEnum.getCategoryIdByName(createPostRequestDto.getSelectedCategory());


            Post p = Post.builder()
                    .account(new Account(accountId))
                    .createDate(timeProvider.currentDateTime())
                    .text(createPostRequestDto.getText())
                    .image(createPostRequestDto.getImage())
                    .category(new Category(categoryId))
                    .build();

            p = postRepository.save(p);

            if (p.getId() != null ) {

                return CreatePostResponseDto.builder()
                        .success(true)
                        .message("Utworzono post")
                        .build();
            } else {
                throw new PostException(PostErrorCodes.CREATE_POST_ERROR);
            }

        } else {
            throw new PostException(PostErrorCodes.SESSION_EXPIRED);
        }

    }
}

