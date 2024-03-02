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

    public CommentLike(CommentLikeBuilder commentLikeBuilder) {
        this.id = commentLikeBuilder.id;
        this.account = commentLikeBuilder.account;
        this.comment = commentLikeBuilder.comment;
        this.createDate = commentLikeBuilder.createDate;

    }

    public CommentLikesEntity translate() {
        return CommentLikesEntity.builder()
                .id(this.id)
                .account(this.account.translateId())
                .comment(this.comment.translateId())
                .createDate(this.createDate)
                .build();
    }

    public static class CommentLikeBuilder {

        private Integer id;
        private Account account;
        private Comment comment;
        private LocalDateTime createDate;

        public CommentLikeBuilder() {
        }

        public CommentLikeBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public CommentLikeBuilder setAccount(Account account) {
            this.account = account;
            return this;
        }

        public CommentLikeBuilder setComment(Comment comment) {
            this.comment = comment;
            return this;
        }

        public CommentLikeBuilder setCreateDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }
        public CommentLike build() {
            return new CommentLike(this);
        }
    }



}
