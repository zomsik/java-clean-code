package pollub.projekt.ddd.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.xml.bind.DatatypeConverter;
import lombok.Data;
import org.joda.time.LocalDateTime;
import pollub.projekt.ddd.common.patterns.interpreter.GetDateWithAddedSecondsExpression;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Data
public class JwtUtil {

    private String secretKey = "T5XnG1eMelkRZRTggV3DiyTRYKz0hfkwMVKfKstQbhVL60Sc";

    private String issuer = "ProjectApplicationServer";

    private String expSeconds = "900";




    /* Tydzień 2, Wzorzec Singleton

    Wzorzec Singleton tworzy jedną instancję danej klasy dla całego projektu. Pierwsze użycie tworzy klasę,
    a każde kolejne przekazuja już istniejący obiekt zapisany pod zmienną statyczną instance w tej klasie.
    Pobranie tej samej instancji obiektu  z innej klasy: this.jwtUtil = JwtUtil.getInstance();

    Koniec, Tydzień 2, Wzorzec Singleton */

    private static JwtUtil instance;

    private static GetDateWithAddedSecondsExpression getDateWithAddedSecondsExpression;

    public static JwtUtil getInstance() {
        if (instance == null) {
            instance = new JwtUtil();
        }
        return instance;
    }

    private JwtUtil() {
    }


    public String createJWT(String subject) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        getDateWithAddedSecondsExpression = new GetDateWithAddedSecondsExpression(getExpSeconds());

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(new LocalDateTime(nowMillis).toDate())
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(getDateWithAddedSecondsExpression.interpret());

        return builder.compact();
    }

    public boolean valid(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public String getUser(String jwt) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();

            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }

    }

}
