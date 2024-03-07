package pollub.projekt.ddd.account.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleResponse implements Response {
    private String message;
    private boolean success;

    @Override
    public Map<String, Object> body() {
        return Map.of("message", message, "success", success);
    }
}
