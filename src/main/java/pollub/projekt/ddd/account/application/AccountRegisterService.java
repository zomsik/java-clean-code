package pollub.projekt.ddd.account.application;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pollub.projekt.ddd.account.domain.Account;
import pollub.projekt.ddd.account.domain.AccountRepository;
import pollub.projekt.ddd.account.domain.RoleEnum;
import pollub.projekt.ddd.account.domain.exception.AccountErrorCodes;
import pollub.projekt.ddd.account.domain.exception.AccountException;
import pollub.projekt.ddd.account.rest.dto.RegisterRequestDto;
import pollub.projekt.ddd.account.rest.dto.RegisterResponseDto;
import pollub.projekt.ddd.common.application.time.TimeProvider;
import pollub.projekt.ddd.common.patterns.factory.ResponseEnum;
import pollub.projekt.ddd.common.patterns.factory.ResponseFactory;
import pollub.projekt.ddd.common.patterns.factory.ResponseInterface;
import pollub.projekt.ddd.common.utils.JwtUtil;

@Service
public class AccountRegisterService {

    private final JwtUtil jwtUtil;
    private final AccountRepository accountRepository;
    private final TimeProvider timeProvider;

    public AccountRegisterService(AccountRepository accountRepository, TimeProvider timeProvider) {
        this.jwtUtil = JwtUtil.getInstance();
        this.accountRepository = accountRepository;
        this.timeProvider = timeProvider;
    }

    public ResponseInterface register (RegisterRequestDto request) {

        if (!accountRepository.existsByLogin(request.getLogin())) {

            String hashedPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());

            Account newAcc = accountRepository.save(Account.builder()
                    .username(request.getLogin())
                    .password(hashedPassword)
                    .role(RoleEnum.USER.id)
                    .email(request.getEmail())
                    .birthDate(request.getBirthDate())
                    .registerDate(timeProvider.currentDateTime())
                    .build());

            if(newAcc.getId() != null) {
                String jwt = jwtUtil.createJWT(request.getLogin());

                if (jwt != null) {
                    return RegisterResponseDto.builder()
                            .success(true)
                            .token(jwt)
                            .build();
                } else {

                    return ResponseFactory.createResponse(ResponseEnum.REGISTER, false, "Błąd logowania po rejestracji" , null);
                }
            } else {
                throw new AccountException(AccountErrorCodes.REGISTER_ERROR);
            }


        } else {
            throw new AccountException(AccountErrorCodes.USERNAME_TAKEN);
        }
    }
}
