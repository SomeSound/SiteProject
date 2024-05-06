package br.com.hyper.utils;

import br.com.hyper.entities.CustomerEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JwtTokenUtil {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(CustomerEntity customer) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Date expirationDate = generateExpirationDate();
            return JWT.create()
                    .withIssuer("Hyper")
                    .withSubject(customer.getEmail())
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);

        } catch(JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String refreshToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            System.out.println(token);
            String email = JWT.require(algorithm)
                    .withIssuer("Hyper")
                    .build()
                    .verify(token)
                    .getSubject();
            Date expirationDate = generateExpirationDate();
            return JWT.create()
                    .withIssuer("Hyper")
                    .withSubject(email)
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error while refreshing token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Hyper")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error while validate token", exception);
        }
    }

    private Date generateExpirationDate() {
        LocalDateTime localDateTime = LocalDateTime.now().plusHours(2); // 2 horas de expiração
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }
}
