package pollub.projekt;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pollub.projekt.ddd.common.utils.JwtUtil;


@UnitTest
public class JwtUtilTests implements WithAssertions {

    /* Tydzień 13, Testy

        Testy dla wystawiania, walidacji i odczytywania Json Web Token (jwt).

    Koniec, Tydzień 13, Testy */

    @Test
    public void getSingletonJwtUtilClassIsNotNull() {
        Assertions.assertNotNull(JwtUtil.getInstance());
    }


    @Test
    public void createdTokenIsNotNull() {
        JwtUtil jwtUtil = JwtUtil.getInstance();
        String jwt = jwtUtil.createJWT("test-subject");

        Assertions.assertNotNull(jwt);
    }

    @Test
    public void createdTokenSubjectIsInJWT() {
        JwtUtil jwtUtil = JwtUtil.getInstance();
        String subject = "test-subject";

        String jwt = jwtUtil.createJWT(subject);

        Assertions.assertEquals(subject, jwtUtil.getUser(jwt));
    }

    @Test
    public void createdTokenIsValid() {
        JwtUtil jwtUtil = JwtUtil.getInstance();
        String jwt = jwtUtil.createJWT("test-subject");

        Assertions.assertTrue(jwtUtil.valid(jwt));
    }


    @Test
    public void createdTokenIsExpired() {
        JwtUtil jwtUtil = JwtUtil.getInstance();
        jwtUtil.setExpMillis("1");
        String jwt = jwtUtil.createJWT("test-subject");
        jwtUtil.setExpMillis("90000");
        Assertions.assertFalse(jwtUtil.valid(jwt));
    }



}
