package vn.aptech.security.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import vn.aptech.security.auth.CustomUserDetailImpl;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTTokenService {
    private final static String JWT_SECRET = "$M7~q+^Vd@4Px.%j#)d#sL(aKzU.63E3'_DTC3r9,OngSA]CVF";
    private final Long expiration = 86400000L;

    private final SecretKey secretKey = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

    public String generateToken(CustomUserDetailImpl user) {
        Date now = new Date();

        Date expiryDate = new Date(now.getTime() + expiration);
        Map<String, String> claims = new HashMap<>();
        claims.put("userId", user.getId().toString());
        claims.put("name", user.getName());
        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(expiryDate)
                .issuedAt(now)
                .claims(claims)
                .signWith(secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getExpiration() {
        return expiration;
    }
}
