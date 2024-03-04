package pollub.projekt.ddd.common.patterns.bridge;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


public class ClientUserCreator implements UserCreator {




    @Override
    public UserDetails createUser(String username, String password, PasswordEncoder passwordEncoder) {
        return User
                .withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles("CLIENT").build();
    }
}
