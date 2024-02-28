package pollub.projekt.ddd.post.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pollub.projekt.ddd.account.infrastructure.jpa.AccountEntity;
import pollub.projekt.ddd.comment.infrastructure.jpa.CommentEntity;
import pollub.projekt.ddd.post.domain.Post;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "post", schema = "public")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "deleted_by")
    private Integer deletedBy;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private List<CommentEntity> comments;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private List<PostLikesEntity> postLikes;

    public Post translate() {
        return Post.builder()
                .id(this.id != null ? this.id : null)
                .account(this.account != null ? this.account.translate() : null)
                .text(this.text != null ? this.text : null)
                .image(this.image != null ? this.image : null)
                .createDate(this.createDate != null ? this.createDate : null)
                .deleted(this.deleted)
                .deletedBy(this.deletedBy != null ? this.deletedBy : null)
                .deletedDate(this.deletedDate != null ? this.deletedDate : null)
                .comments(this.comments != null ? this.comments.size() : 0)
                .likes(this.postLikes != null ? this.postLikes.size() : 0)
                .category(this.category != null ? this.category.translate() : null)
                .build();
    }

    public Post translateId() {
        return Post.builder()
                .id(this.id)
                .build();
    }

}
