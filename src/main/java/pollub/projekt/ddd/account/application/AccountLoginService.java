package pollub.projekt.ddd.account.application;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pollub.projekt.ddd.account.domain.AccountRepository;
import pollub.projekt.ddd.account.domain.exception.AccountErrorCodes;
import pollub.projekt.ddd.account.domain.exception.AccountException;
import pollub.projekt.ddd.account.rest.dto.LoginRequestDto;
import pollub.projekt.ddd.account.rest.dto.LoginResponseDto;
import pollub.projekt.ddd.common.patterns.factory.ResponseEnum;
import pollub.projekt.ddd.common.patterns.factory.ResponseFactory;
import pollub.projekt.ddd.common.patterns.factory.ResponseInterface;
import pollub.projekt.ddd.common.utils.JwtUtil;

@Service
public class AccountLoginService {

    private final JwtUtil jwtUtil;
    private final AccountRepository accountRepository;

    public AccountLoginService(AccountRepository accountRepository) {
        this.jwtUtil = JwtUtil.getInstance();
        this.accountRepository = accountRepository;
    }

    public ResponseInterface login(LoginRequestDto request) {



        if (accountRepository.existsByLogin(request.getLogin())) {

            String passHash = accountRepository.getPasswordByLogin(request.getLogin());
            if (BCrypt.checkpw(request.getPassword(), passHash)) {
                String jwt = jwtUtil.createJWT(request.getLogin());
                if (jwt != null) {
                    return ResponseFactory.createResponse(ResponseEnum.LOGIN, true, null , jwt);

                } else {


                    return LoginResponseDto.builder()
                            .success(false)
                            .message("Błąd logowania")
                            .build();
                }
            } else {
                throw new AccountException(AccountErrorCodes.WRONG_PASSWORD);
            }

        } else {
            throw new AccountException(AccountErrorCodes.USER_NOT_FOUND);
        }
    }
}
