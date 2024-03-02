package pollub.projekt.ddd.common.patterns.factory;

import pollub.projekt.ddd.account.rest.dto.LoginResponseDto;
import pollub.projekt.ddd.account.rest.dto.RegisterResponseDto;


/* Tydzień 1, Wzorzec Factory

    Wzorzec Factory tworzy dla nas obiekty bez konieczności ujawniania szczegółów ich powstawania. W tym przypadku
    tworzone są odpowiedzi serwera z domeny account dla logowania i rejestracji na podstawie przykazania odpowiedniego
    typu z enuma do fabryki

Koniec, Tydzień 1, Wzorzec Factory */

public class ResponseFactory {

    public static ResponseInterface createResponse(ResponseEnum type, boolean success, String message, String token) {
        if (ResponseEnum.LOGIN.equals(type)) {
            return new LoginResponseDto(success, message, token);
        } else if (ResponseEnum.REGISTER.equals(type)) {
            return new RegisterResponseDto(success, message, token);
        } else {
            throw new IllegalArgumentException("Invalid response enum type");
        }
    }

}
