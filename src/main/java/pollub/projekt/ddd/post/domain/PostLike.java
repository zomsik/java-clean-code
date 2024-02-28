package pollub.projekt.ddd.post.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.post.infrastructure.jpa.PostLikesEntity;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class PostLike {

    private Integer id;
    private Account account;
    private Post post;
    private LocalDateTime createDate;

    public PostLikesEntity translate() {
        return PostLikesEntity.builder()
                .id(this.id)
                .account(this.account.translateId())
                .post(this.post.translateId())
                .createDate(this.createDate)
                .build();
    }


}
