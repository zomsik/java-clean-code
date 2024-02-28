package pollub.projekt.ddd.comment.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.comment.infrastructure.jpa.CommentLikesEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommentLike {

    private Integer id;
    private Account account;
    private Comment comment;
    private LocalDateTime createDate;

    public CommentLikesEntity translate() {
        return CommentLikesEntity.builder()
                .id(this.id)
                .account(this.account.translateId())
                .comment(this.comment.translateId())
                .createDate(this.createDate)
                .build();
    }

}
