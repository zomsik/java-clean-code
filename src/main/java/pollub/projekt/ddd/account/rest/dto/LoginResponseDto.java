package pollub.projekt.ddd.account.rest.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponseDto implements Serializable {
    private boolean success;
    private String message;
    private String token;
}
