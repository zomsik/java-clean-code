package pollub.projekt.ddd.comment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.projekt.ddd.comment.domain.Comment;
import pollub.projekt.ddd.comment.domain.CommentRepository;
import pollub.projekt.ddd.common.application.account.AccountFacade;
import pollub.projekt.ddd.common.application.comment.CommentDto;
import pollub.projekt.ddd.common.utils.JwtUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCommentsService {

    private final CommentRepository commentRepository;
    private final AccountFacade accountFacade;
    private final JwtUtil jwtUtil;
    public List<CommentDto> getComments(Integer postId, String jwt) {
        if (postId == null) {
            return new ArrayList<>();
        }

        List<CommentDto> commentsList = commentRepository.getComments(postId).stream().map(Comment::translateToDto).toList();

        if (jwtUtil.valid(jwt)) {
            String user = jwtUtil.getUser(jwt);
            Integer accountId = accountFacade.getIdByLogin(user);

            for (CommentDto comment : commentsList) {
                if (commentRepository.isCommentLikedByUser(comment.getId(), accountId))
                    comment.setLiked(true);
            }

        }

        return commentsList;
    }

}
