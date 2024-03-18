package pollub.projekt.infrastructure.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pollub.projekt.ddd.common.patterns.bridge.*;

@Configuration
@RequiredArgsConstructor
@EnableWebMvc
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${custom.api.user}")
    private String username;
    @Value("${custom.api.password}")
    private String password;
    @Value("${custom.swagger.user}")
    private String usernameSwagger;
    @Value("${custom.swagger.password}")
    private String passwordSwagger;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected UserDetailsService configure() {

        /* Tydzień 3, Wzorzec Bridge

        Wzorzec Bridge pozwala na separację abstrakcji od implementacji.
        Można na nich pracować niezależnie.

        Koniec, Tydzień 3, Wzorzec Bridge */

        UserCreator swaggerUserCreator = new SwaggerUserCreator();
        AbstractUser swaggerUser = new SwaggerUser(swaggerUserCreator);

        UserCreator clientUserCreator = new ClientUserCreator();
        AbstractUser clientUser = new ClientUser(clientUserCreator);

        return new InMemoryUserDetailsManager(clientUser.createUser(username, password, passwordEncoder()),
                swaggerUser.createUser(usernameSwagger, passwordSwagger, passwordEncoder()));
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/account/**",
                                         "/comments/**",
                                         "/posts/**").hasRole("CLIENT")
                        .requestMatchers("/swagger-ui/**",
                                         "v3/api-docs/**",
                                         "/api/swagger-ui/**").hasRole("SWAGGER")
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().denyAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
