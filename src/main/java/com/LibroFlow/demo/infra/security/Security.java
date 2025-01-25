package com.LibroFlow.demo.infra.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Security {
    private final Filter filter;

    public Security(Filter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //BOOKS
                        .requestMatchers(HttpMethod.POST, "/books").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/books/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/books/{title}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/books/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/books/{id}").hasAuthority("ADMIN")

                        //USER
                        .requestMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users/register").hasAuthority("ADMIN")

                       //LOGIN
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()

                        //BORROW
                        .requestMatchers(HttpMethod.POST, "/borrowbooks").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/borrowbooks").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/borrowbooks/return").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/borrowbooks/return").hasAuthority("ADMIN")

                        //SWAGGER
                        .requestMatchers("/v3/api-docs/**","/swagger-ui.html", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()

                )

                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
