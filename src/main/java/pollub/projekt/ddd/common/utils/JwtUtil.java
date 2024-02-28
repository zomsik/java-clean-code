package pollub.projekt.ddd.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.xml.bind.DatatypeConverter;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Component
public class JwtUtil {

    @Value("${custom.jwt.secret}")
    private String secretKey;

    @Value("${custom.jwt.issuer}")
    private String issuer;

    @Value("${custom.jwt.expiration}")
    private String expMillis;


    public String createJWT(String subject) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(new LocalDateTime(nowMillis).toDate())
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(new LocalDateTime(expMillis).toDate());

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
