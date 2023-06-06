package com.dev.security;

import com.dev.domain.UsuarioPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {


    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L; // 30 days

    public String generateToken(Authentication authentication) {
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        SignatureAlgorithm algorithm = SignatureAlgorithm.HS512; // o el algoritmo que desees utilizar
      //  SecretKey secretKey = Keys.secretKeyFor(algorithm);

        return Jwts.builder()
                .setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY, algorithm)
                .compact();
    }

//    public String generateToken(String username) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000);
//
//        JwtBuilder jwtBuilder = Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SECRET_KEY);
//
//        return jwtBuilder.compact();
//    }

    public String getUsernameFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);

        return claimsJws.getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;

        }catch (MalformedJwtException e){
            log.error("token mal formado");
        }catch (UnsupportedJwtException e){
            log.error("token no soportado");
        }catch (ExpiredJwtException e){
            log.error("token expirado");
        }catch (IllegalArgumentException e){
            log.error("token vacío");
        }catch (SignatureException e){
            log.error("fail en la firma");
        }
            return false;
        }


    }

