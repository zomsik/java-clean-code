package pollub.projekt.ddd.account.rest.dto;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ResponseBaseDecorator implements Response {
    private final Response response;

    @Override
    public Map<String, Object> body() {
        return response.body();
    }
}
