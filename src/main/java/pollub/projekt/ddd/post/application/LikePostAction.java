package pollub.projekt.ddd.post.application;

import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.common.application.account.AccountFacade;
import pollub.projekt.ddd.common.application.time.TimeProvider;
import pollub.projekt.ddd.post.domain.Post;
import pollub.projekt.ddd.post.domain.PostLike;
import pollub.projekt.ddd.post.domain.PostLikesRepository;
import pollub.projekt.ddd.post.domain.PostRepository;
import pollub.projekt.ddd.post.domain.exception.PostErrorCodes;
import pollub.projekt.ddd.post.domain.exception.PostException;
import pollub.projekt.ddd.post.rest.dto.LikeResponseDto;

public class LikePostAction extends LikeActionTemplate{

    private final PostLikesRepository postLikesRepository;
    private final TimeProvider timeProvider;
    public LikePostAction(AccountFacade accountFacade, PostRepository postRepository,
                          PostLikesRepository postLikesRepository, TimeProvider timeProvider) {
        super(accountFacade, postRepository);
        this.postLikesRepository = postLikesRepository;
        this.timeProvider = timeProvider;
    }

    @Override
    protected LikeResponseDto performAction(Integer postId, Integer accountId, Object... additionalParams) {
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

    }
}
