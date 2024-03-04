package pollub.projekt.ddd.common.patterns.bridge;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public abstract class AbstractUser {

    final UserCreator userCreator;

    abstract public UserDetails createUser(String username, String password, PasswordEncoder passwordEncoder);



}
