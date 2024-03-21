package pollub.projekt.ddd.account.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.account.domain.RoleStateFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "account", schema = "public")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    public Account translate() {
        return Account.builder()
                .id(this.id != null ? this.id : null)
                .username(this.username != null ? this.username : null)
                .password(this.password != null ? this.password : null)
                .email(this.email != null ? this.email : null)
                .role(RoleStateFactory.getRoleState(this.role)) // Używanie fabryki do przypisania roli
                .birthDate(this.birthDate != null ? this.birthDate : null)
                .registerDate(this.registerDate != null ? this.registerDate : null)
                .build();
    }
    public Account translateId() {
        return Account.builder()
                .id(this.id)
                .build();
    }

}
