package pollub.projekt.ddd.comment.domain;

import lombok.*;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.comment.infrastructure.jpa.CommentEntity;
import pollub.projekt.ddd.common.application.comment.CommentDto;
import pollub.projekt.ddd.post.domain.Post;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Integer id;
    private Account account;
    private Post post;
    private String text;
    private LocalDateTime createDate;
    private boolean deleted;
    private Integer deletedBy;
    private LocalDateTime deletedDate;
    private Integer likes;

    public CommentEntity translate() {
        return CommentEntity.builder()
                .id(this.id)
                .account(this.account.translate())
                .post(this.post.translate())
                .text(this.text)
                .createDate(this.createDate)
                .deleted(this.deleted)
                .deletedBy(this.deletedBy)
                .deletedDate(this.deletedDate)
                .build();
    }

    public CommentEntity translateId() {
        return CommentEntity.builder()
                .id(this.id)
                .build();
    }


    public CommentDto translateToDto() {
        return CommentDto.builder()
                .id(this.id)
                .account(this.account.translateToDto())
                .postId(this.post.getId())
                .text(this.text)
                .createDate(this.createDate)
                .likes(this.likes)
                .liked(false)
                .build();
    }

    public Comment(Integer id) {
        this.id = id;
    }


}
