package craveTechBackend.Movie.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // 🔹 TEMP SIMPLE USER (replace with DB later)
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password("") // password not needed for JWT validation
                .authorities("USER")
                .build();
    }
}
