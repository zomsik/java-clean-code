package pollub.projekt.ddd.post.application;

import pollub.projekt.ddd.common.application.account.AccountFacade;
import pollub.projekt.ddd.post.domain.PostLikesRepository;
import pollub.projekt.ddd.post.domain.PostRepository;
import pollub.projekt.ddd.post.domain.exception.PostErrorCodes;
import pollub.projekt.ddd.post.domain.exception.PostException;
import pollub.projekt.ddd.post.rest.dto.LikeResponseDto;

public class DislikePostAction extends LikeActionTemplate{
    private final PostLikesRepository postLikesRepository;

    public DislikePostAction(AccountFacade accountFacade, PostRepository postRepository,
                             PostLikesRepository postLikesRepository) {
        super(accountFacade, postRepository);
        this.postLikesRepository = postLikesRepository;
    }

    @Override
    protected LikeResponseDto performAction(Integer postId, Integer accountId, Object... additionalParams) {
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
    }
}
