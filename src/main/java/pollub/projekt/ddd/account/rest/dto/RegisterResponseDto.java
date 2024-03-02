package pollub.projekt.ddd.account.rest.dto;

import lombok.*;
import pollub.projekt.ddd.common.patterns.factory.ResponseInterface;

import java.io.Serializable;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterResponseDto implements Serializable, ResponseInterface {

    private boolean success;
    private String message;
    private String token;
}
