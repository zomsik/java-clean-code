package pollub.projekt.ddd.common.patterns.bridge;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserCreator {

    UserDetails createUser(String username, String password, PasswordEncoder passwordEncoder);
}
