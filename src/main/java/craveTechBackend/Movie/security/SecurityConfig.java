package craveTechBackend.Movie.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth

                // 🔓 Auth APIs
                .requestMatchers(
                        "/api/auth/login",
                        "/api/auth/register"
                ).permitAll()

                // 🔓 Public movie listing
                .requestMatchers(HttpMethod.GET, "/api/movies").permitAll()

                // 🔐 Movie create & delete (JWT required)
                .requestMatchers("/api/movies/**").authenticated()

                // ❌ Everything else blocked
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
