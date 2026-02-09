package craveTechBackend.Books.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET =
            Base64.getEncoder().encodeToString(
                    "cravetech_super_secret_key_2026_cravetech".getBytes()
            );

    private static final long EXPIRATION_TIME = 86400000;

    private Key getSignKey() {

        byte[] keyBytes = Base64.getDecoder().decode(SECRET);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + EXPIRATION_TIME)
                )
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
