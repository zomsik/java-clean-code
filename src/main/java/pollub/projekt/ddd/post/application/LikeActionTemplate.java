package pollub.projekt.ddd.post.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pollub.projekt.ddd.account.domain.UserRole;
import pollub.projekt.ddd.common.application.account.AccountFacade;
import pollub.projekt.ddd.common.utils.JwtUtil;
import pollub.projekt.ddd.post.domain.PostRepository;
import pollub.projekt.ddd.post.domain.exception.PostErrorCodes;
import pollub.projekt.ddd.post.domain.exception.PostException;
import pollub.projekt.ddd.post.rest.dto.LikeResponseDto;

public abstract class LikeActionTemplate {

    protected final JwtUtil jwtUtil = JwtUtil.getInstance();
    protected final AccountFacade accountFacade;
    protected final PostRepository postRepository;
    private static final Logger logger = LoggerFactory.getLogger(LikeActionTemplate.class);

    public LikeActionTemplate(AccountFacade accountFacade, PostRepository postRepository) {
        this.accountFacade = accountFacade;
        this.postRepository = postRepository;
    }

    // Metoda szablonowa
    public final LikeResponseDto execute(String jwt, Integer postId, Object... additionalParams) {
        if (!jwtUtil.valid(jwt)) {
            throw new PostException(PostErrorCodes.SESSION_EXPIRED);
        }
        logger.info("Użycie wzorca template w projekcie");
        String user = jwtUtil.getUser(jwt);
        Integer accountId = accountFacade.getIdByLogin(user);
        return performAction(postId, accountId, additionalParams);
    }


    protected abstract LikeResponseDto performAction(Integer postId, Integer accountId, Object... additionalParams);
}
