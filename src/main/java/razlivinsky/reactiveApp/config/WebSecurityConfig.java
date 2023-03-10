package razlivinsky.reactiveApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository contextRepository;

    public WebSecurityConfig(AuthenticationManager authenticationManager,
                             SecurityContextRepository contextRepository) {
        this.authenticationManager = authenticationManager;
        this.contextRepository = contextRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .exceptionHandling()
                .authenticationEntryPoint(
                        (swe, e) ->
                                Mono.fromRunnable(
                                        () -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)
                                )
                )
                .accessDeniedHandler(
                        (swe, e) ->
                                Mono.fromRunnable(
                                        () -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)
                                )
                )
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(contextRepository)
                .authorizeExchange()
                .pathMatchers("/", "/login", "/favicon.ico")
                .permitAll()
                .pathMatchers("/controller")
                .hasRole("ADMIN")
                .anyExchange()
                .authenticated()
                .and()
                .build();
    }
}
