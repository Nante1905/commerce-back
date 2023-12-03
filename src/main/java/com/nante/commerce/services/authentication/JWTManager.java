package com.nante.commerce.services.authentication;

import java.security.Key;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.nante.commerce.model.employe.Employe;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTManager {
    private static final String secret = "DvxMWzlQ2d6zSQ77EseNcGI1x0hhpCVJwtXBIx4c7uUlDSSRCD4kBXFyzEY2zLdN";
    private static final Key key = new SecretKeySpec(Base64.getDecoder().decode(secret),
            SignatureAlgorithm.HS256.getJcaName());

    public String generateToken(Employe employe, Collection<? extends GrantedAuthority> authorities) {
        Date currentDate = new Date();
        String auths = authorities.stream().map(a -> a.getAuthority()).toList().toString();
        auths = auths.substring(1, auths.length() - 1);

        String token = Jwts.builder()
                .setSubject(employe.getNom())
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + dayToMs(1)))
                .claim("email", employe.getEmail())
                .claim("id", employe.getId())
                .claim("authorization", auths)
                .signWith(key)
                .compact();
        return token;
    }

    public String getEmail(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("email", String.class);
    }

    public boolean validateToken(String token) throws AuthenticationCredentialsNotFoundException {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("Invalid token",
                    ex.fillInStackTrace());
        }
    }

    private long dayToMs(long day) {
        return 1000 * day * 24 * 60 * 60;
    }
}
