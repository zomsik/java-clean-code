package pollub.projekt.ddd.account.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto implements Serializable {

    private String login;
    private String password;
    private String email;
    private LocalDate birthDate;

}
