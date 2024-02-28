package pollub.projekt.ddd.comment.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pollub.projekt.ddd.account.infrastructure.jpa.AccountEntity;
import pollub.projekt.ddd.comment.domain.Comment;
import pollub.projekt.ddd.post.infrastructure.jpa.PostEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comment", schema = "public")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @Column(name = "text")
    private String text;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "deleted_by")
    private Integer deletedBy;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private List<CommentLikesEntity> commentLikes;


    public Comment translate() {
        return Comment.builder()
                .id(this.id != null ? this.id : null)
                .account(this.account != null ? this.account.translate() : null)
                .post(this.post != null ? this.post.translate() : null)
                .text(this.text != null ? this.text : null)
                .createDate(this.createDate != null ? this.createDate : null)
                .deleted(this.deleted)
                .deletedBy(this.deletedBy != null ? this.deletedBy : null)
                .deletedDate(this.deletedDate != null ? this.deletedDate : null)
                .likes(this.commentLikes != null ? this.commentLikes.size() : 0)
                .build();
    }


    public Comment translateId() {
        return Comment.builder()
                .id(this.id)
                .build();
    }

}
