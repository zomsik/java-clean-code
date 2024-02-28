package pollub.projekt.ddd.post.domain;

import lombok.*;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.common.application.post.PostDto;
import pollub.projekt.ddd.post.infrastructure.jpa.PostEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private Integer id;
    private Account account;
    private String text;
    private byte[] image;
    private LocalDateTime createDate;
    private boolean deleted;
    private Integer deletedBy;
    private LocalDateTime deletedDate;
    private Category category;
    private Integer comments;
    private Integer likes;

    public PostEntity translate() {
        return PostEntity.builder()
                .id(this.id != null ? this.id : null)
                .account(this.account != null ? this.account.translate() : null)
                .text(this.text != null ? this.text : null)
                .image(this.image != null ? this.image : null)
                .createDate(this.createDate != null ? this.createDate : null)
                .deleted(this.deleted)
                .deletedBy(this.deletedBy != null ? this.deletedBy : null)
                .deletedDate(this.deletedDate != null ? this.deletedDate : null)
                .category(this.category != null ? this.category.translate() : null)
                .build();
    }

    public PostDto translateToDto() {
        return PostDto.builder()
                .id(this.id)
                .account(this.account.translateToDto())
                .text(this.text)
                .createDate(this.createDate)
                .likes(this.likes)
                .comments(this.comments)
                .liked(false)
                .build();
    }

    public PostEntity translateId() {
        return PostEntity.builder()
                .id(this.id)
                .build();
    }

    public Post(Integer id) {
        this.id = id;
    }


}
