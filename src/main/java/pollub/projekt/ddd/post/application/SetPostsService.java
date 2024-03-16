package pollub.projekt.ddd.post.application;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.common.application.account.AccountFacade;
import pollub.projekt.ddd.common.application.time.TimeProvider;
import pollub.projekt.ddd.common.utils.JwtUtil;
import pollub.projekt.ddd.post.domain.*;
import pollub.projekt.ddd.post.domain.exception.PostErrorCodes;
import pollub.projekt.ddd.post.domain.exception.PostException;
import pollub.projekt.ddd.post.rest.dto.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Component
public class SetPostsService {

    private final AccountFacade accountFacade;
    private final PostRepository postRepository;
    private final PostLikesRepository postLikesRepository;
    private final JwtUtil jwtUtil;
    private final TimeProvider timeProvider;
    private final List<Memento<Post>> mementoList;

    public SetPostsService(AccountFacade accountFacade, PostRepository postRepository,
                           PostLikesRepository postLikesRepository, TimeProvider timeProvider) {
        this.accountFacade = accountFacade;
        this.postRepository = postRepository;
        this.postLikesRepository = postLikesRepository;
        this.jwtUtil = JwtUtil.getInstance();
        this.timeProvider = timeProvider;
        mementoList = new ArrayList<>();
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
                PostOriginator originator = new PostOriginator(p, postRepository);
                try {
                    mementoList.add(originator.save());
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }

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

    public UpdatePostResponseDto updatePost(UpdatePostRequestDto updatePostRequestDto, String jwt) {
        if (!jwtUtil.valid(jwt)) {
            throw new PostException(PostErrorCodes.SESSION_EXPIRED);
        }

        if (updatePostRequestDto.getText() == null || updatePostRequestDto.getText().isEmpty()) {
            throw new PostException(PostErrorCodes.FIELDS_NOT_FILLED);
        }

        String user = jwtUtil.getUser(jwt);
        Integer accountId = accountFacade.getIdByLogin(user);
        Post post = postRepository.getById(updatePostRequestDto.getPostId()).orElseThrow(() -> new PostException(PostErrorCodes.WRONG_POST_ID));

        if(!Objects.equals(accountId, post.getAccount().getId())) {
            throw new PostException(PostErrorCodes.INVALID_ACCOUNT_ERROR);
        }

        post.setText(updatePostRequestDto.getText());
        postRepository.save(post);

        PostOriginator originator = new PostOriginator(post, postRepository);
        try {
            mementoList.add(originator.save());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        return UpdatePostResponseDto.builder()
                .success(true)
                .message("Zedytowano post")
                .build();
    }

    public RestorePostResponseDto restorePost(RestorePostRequestDto restorePostRequestDto, String jwt) {
        if (!jwtUtil.valid(jwt)) {
            throw new PostException(PostErrorCodes.SESSION_EXPIRED);
        }

        String user = jwtUtil.getUser(jwt);
        Integer accountId = accountFacade.getIdByLogin(user);
        Post post = postRepository.getById(restorePostRequestDto.getPostId()).orElseThrow(() -> new PostException(PostErrorCodes.WRONG_POST_ID));

        if(!Objects.equals(accountId, post.getAccount().getId())) {
            throw new PostException(PostErrorCodes.INVALID_ACCOUNT_ERROR);
        }

        /* Tydzień 5, Wzorzec Memento

        Memento przechowuje i pozwala na zapisywanie, i przywracanie stanu obiektu (w tym przypadku przywrócenie sprzed edycji postu).
        Wzorzec składa się z PostOriginatora zapisującego i przywracającego post oraz Post przechowującego stan obiektu i PostMemento pełniącego rolę Caretakera - umożlwia przywrócenie stanu poprzedniego.

        Koniec, Tydzień 5, Wzorzec Memento */

        Memento<Post> memento = getMemento(restorePostRequestDto.getPostId(), restorePostRequestDto.getDate());
        if(memento == null) {
            throw new PostException(PostErrorCodes.RESTORE_POINT_NOT_FOUNT);
        }
        PostOriginator postOriginator = new PostOriginator(post, postRepository);
        postOriginator.restore(memento);

        return RestorePostResponseDto.builder()
                .success(true)
                .message("Przywrócono post")
                .build();
    }

    private Memento<Post> getMemento(Integer postId, LocalDateTime localDateTime) {
        Memento<Post> selectedMemento = null;
        Long minDiff = Long.MAX_VALUE;
        for(var memento : mementoList) {
            if(memento.getState().getId().equals(postId)) {
                long diff = Math.abs(ChronoUnit.SECONDS.between(localDateTime, memento.getDateCreated()));
                if(selectedMemento == null || diff < minDiff) {
                    minDiff = diff;
                    selectedMemento = memento;
                }
            }
        }
        return selectedMemento;
    }
}

