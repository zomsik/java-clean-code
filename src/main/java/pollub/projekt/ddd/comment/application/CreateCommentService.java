package pollub.projekt.ddd.comment.application;

import org.springframework.stereotype.Service;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.comment.domain.Comment;
import pollub.projekt.ddd.comment.domain.CommentRepository;
import pollub.projekt.ddd.comment.domain.exception.CommentErrorCodes;
import pollub.projekt.ddd.comment.domain.exception.CommentException;
import pollub.projekt.ddd.comment.rest.dto.CreateCommentRequestDto;
import pollub.projekt.ddd.comment.rest.dto.CreateCommentResponseDto;
import pollub.projekt.ddd.common.application.account.AccountFacade;
import pollub.projekt.ddd.common.application.time.TimeProvider;
import pollub.projekt.ddd.common.utils.JwtUtil;
import pollub.projekt.ddd.post.domain.Post;


@Service
public class CreateCommentService {

    private final CommentRepository commentRepository;
    private final AccountFacade accountFacade;
    private final JwtUtil jwtUtil;
    private final TimeProvider timeProvider;

    public CreateCommentService(CommentRepository commentRepository,
                                   AccountFacade accountFacade, TimeProvider timeProvider) {
        this.commentRepository = commentRepository;
        this.accountFacade = accountFacade;
        this.jwtUtil = JwtUtil.getInstance();
        this.timeProvider = timeProvider;
    }



    public CreateCommentResponseDto createComment(CreateCommentRequestDto createCommentRequestDto, String jwt) {

        if (jwtUtil.valid(jwt)) {

            if (createCommentRequestDto.getText() == null || createCommentRequestDto.getText().isEmpty()) {
                throw new CommentException(CommentErrorCodes.FIELDS_NOT_FILLED);
            }

            if (createCommentRequestDto.getPostId() == null || createCommentRequestDto.getPostId() < 0) {
                throw new CommentException(CommentErrorCodes.CREATE_COMMENT_ERROR);
            }

            String user = jwtUtil.getUser(jwt);
            Integer accountId = accountFacade.getIdByLogin(user);

            Comment c = Comment.builder()
                    .account(new Account(accountId))
                    .post(new Post(createCommentRequestDto.getPostId()))
                    .createDate(timeProvider.currentDateTime())
                    .text(createCommentRequestDto.getText())
                    .build();

            c = commentRepository.save(c);

            if (c.getId() != null ) {

                return CreateCommentResponseDto.builder()
                        .success(true)
                        .message("Utworzono komentarz")
                        .build();
            } else {
                throw new CommentException(CommentErrorCodes.CREATE_COMMENT_ERROR);
            }

        } else {
            throw new CommentException(CommentErrorCodes.SESSION_EXPIRED);
        }
    }
}
