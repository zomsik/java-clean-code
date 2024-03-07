package pollub.projekt.ddd.account.rest.dto;

import java.util.HashMap;
import java.util.Map;

public class ResponseTokenDecorator extends ResponseBaseDecorator {
    private final String token;
    public ResponseTokenDecorator(Response response, String token) {
        super(response);
        this.token = token;
    }

    @Override
    public Map<String, Object> body() {
        Map<String, Object> map = new HashMap<>(super.body());
        putToken(map);
        return map;
    }

    private void putToken(Map<String, Object> map) {
        String key = "token";
        if(map.containsKey(key)) {
            int i = 1;
            while(map.containsKey(key + "_" + i)) {
                i++;
            }
            key = key + "_" + i;
        }
        map.put(key, token);
    }
}
