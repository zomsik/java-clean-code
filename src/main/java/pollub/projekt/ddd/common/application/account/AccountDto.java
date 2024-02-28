package pollub.projekt.ddd.common.application.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountDto {

    private Integer id;
    private String username;

}
