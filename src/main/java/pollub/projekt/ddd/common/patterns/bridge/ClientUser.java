package pollub.projekt.ddd.common.patterns.bridge;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ClientUser extends AbstractUser {

    public ClientUser(UserCreator userCreator) {
        super(userCreator);
    }

    @Override
    public UserDetails createUser(String username, String password, PasswordEncoder passwordEncoder) {
        return userCreator.createUser(username, password, passwordEncoder);
    }
}
