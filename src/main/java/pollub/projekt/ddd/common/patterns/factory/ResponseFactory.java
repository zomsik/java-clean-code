package pollub.projekt.ddd.common.patterns.factory;

import pollub.projekt.ddd.account.rest.dto.*;


/* Tydzień 2, Wzorzec Factory

    Wzorzec Factory tworzy dla nas obiekty bez konieczności ujawniania szczegółów ich powstawania. W tym przypadku
    tworzone są odpowiedzi serwera z domeny account dla logowania i rejestracji na podstawie przykazania odpowiedniego
    typu z enuma do fabryki

Koniec, Tydzień 2, Wzorzec Factory */

public class ResponseFactory {

    public static Response createResponse(ResponseEnum type, boolean success, String message, String token) {
        Response response = new SimpleResponse(message, success);
        if(token != null) {
            response = new ResponseTokenDecorator(response, token);
        }

        if (ResponseEnum.LOGIN.equals(type)) {
            return response;
        } else if (ResponseEnum.REGISTER.equals(type)) {
            return response;
        } else {
            throw new IllegalArgumentException("Invalid response enum type");
        }
    }

}
