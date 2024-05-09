package tahub.sdapitahub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final List<String> SWAGGER_WHITELIST = List.of(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources"
    );

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher(request -> SWAGGER_WHITELIST.contains(request.getServletPath()))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(SWAGGER_WHITELIST.toArray(new String[0])).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/signin")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                );

        return http.build();
    }


}