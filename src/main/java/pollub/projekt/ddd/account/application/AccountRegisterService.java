package pollub.projekt.ddd.account.application;

import pollub.projekt.ddd.account.rest.dto.RegisterRequestDto;
import pollub.projekt.ddd.account.rest.dto.Response;

public interface AccountRegisterService {
    Response register (RegisterRequestDto request);
}
