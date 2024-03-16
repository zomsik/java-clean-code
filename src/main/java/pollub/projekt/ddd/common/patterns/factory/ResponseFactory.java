package pollub.projekt.ddd.common.patterns.factory;

import lombok.extern.slf4j.Slf4j;
import pollub.projekt.ddd.account.rest.dto.*;

import java.util.HashMap;
import java.util.Map;


/* Tydzień 2, Wzorzec Factory

    Wzorzec Factory tworzy dla nas obiekty bez konieczności ujawniania szczegółów ich powstawania. W tym przypadku
    tworzone są odpowiedzi serwera z domeny account dla logowania i rejestracji na podstawie przykazania odpowiedniego
    typu z enuma do fabryki

Koniec, Tydzień 2, Wzorzec Factory */

@Slf4j
public class ResponseFactory {

    private static final Map<String, Response> responseCache = new HashMap<>();

    public static Response createResponse(ResponseEnum type, boolean success, String message, String token) {

        if (ResponseEnum.LOGIN.equals(type) || ResponseEnum.REGISTER.equals(type)) {
            /* Tydzień 4, Wzorzec Flyweight

            Wzorzec ten pozwala efektywniej zarządzać dużą ilością obiektów. Użyty tutaj ze względu na dużą ilość odpowiedzi z serwera pozwala na zachowanie stanu
            Response ze względu na treść odpowiedzi. Niewymagane wtedy jest tworzenie co chwilę nowych obiektów odpowiedzi, a używanie już istniejącego.

            Koniec, Tydzień 4, Wzorzec Flyweight */
            Response response = responseCache.computeIfAbsent(message, newResponseEnum -> {
                log.info("Response cached");
                return new SimpleResponse(message, success);
            });
            if (token != null) {
                response = new ResponseTokenDecorator(response, token);
            }
            log.info("Response returned from cache");
            return response;
        } else {
            throw new IllegalArgumentException("Invalid response enum type");
        }
    }

}
