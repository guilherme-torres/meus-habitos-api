package guilherme.meus_habitos_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import guilherme.meus_habitos_api.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {
    @Value("${jwt.config.secret}")
    private String SECRET_KEY;
    private final String ISSUER = "meus-habitos-api";

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withExpiresAt(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00")))
                    .withSubject(user.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("error generating token.", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}
