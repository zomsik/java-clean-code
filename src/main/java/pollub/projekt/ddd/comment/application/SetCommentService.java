package pollub.projekt.ddd.comment.application;

import pollub.projekt.ddd.comment.rest.dto.LikeResponseDto;

public abstract class SetCommentService {

    public abstract LikeResponseDto changeLikeComment(Integer commentId, String jwt);

}
