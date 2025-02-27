package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            String token = JWT.create()
                    .withIssuer("vollmed")
                    .withSubject(usuario.getLogin())
                    .withClaim("roles", usuario.getRol().toString())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaDeExpiacion())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }

    }

    public String getSubject(String token) {
        DecodedJWT decodedJWT =  null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier  = JWT.require(algorithm)
                    .withIssuer("vollmed")
                    .build();
            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw  new RuntimeException("token invalido");
        }

        return decodedJWT.getSubject();

    }

    private Instant generarFechaDeExpiacion(){

        ZoneId zonaHoraria = ZoneId.of("America/New_York"); // Otra forma de representar UTC-5:00 (zona horaria de Nueva York)
        LocalDateTime fechaActual = LocalDateTime.now(zonaHoraria);
        LocalDateTime fechaExpiracion = fechaActual.plusDays(1);
        return fechaExpiracion.toInstant(zonaHoraria.getRules().getOffset(fechaExpiracion));
    }

    }



