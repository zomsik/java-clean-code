package pollub.projekt.ddd.post.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pollub.projekt.ddd.account.infrastructure.jpa.AccountEntity;
import pollub.projekt.ddd.post.domain.PostLike;

import java.time.LocalDateTime;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "post_likes", schema = "public")
public class PostLikesEntity {

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

    @Column(name = "create_date")
    private LocalDateTime createDate;


    public PostLike translate() {
        return PostLike.builder()
                .id(this.id)
                .account(this.account.translateId())
                .post(this.post.translateId())
                .createDate(this.createDate)
                .build();
    }

}
