package pollub.projekt.ddd.account.domain;

import lombok.*;
import pollub.projekt.ddd.account.infrastructure.jpa.AccountEntity;
import pollub.projekt.ddd.common.application.account.AccountDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private RoleState role;
    private LocalDate birthDate;
    private LocalDateTime registerDate;

    public AccountEntity translate() {
        return AccountEntity.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .role(String.valueOf(this.role))
                .birthDate(this.birthDate)
                .registerDate(this.registerDate)
                .build();
    }

    public AccountEntity translateId() {
        return AccountEntity.builder()
                .id(this.id)
                .build();
    }

    public AccountDto translateToDto() {
        return AccountDto.builder()
                .id(this.id)
                .username(this.username)
                .build();
    }
    public void setRole(RoleState role) {
        this.role = role;
    }

    public RoleState getRole() {
        return this.role;
    }

    public Account(Integer id) {
        this.id = id;
    }

}
