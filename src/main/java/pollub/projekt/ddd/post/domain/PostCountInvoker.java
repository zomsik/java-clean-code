package pollub.projekt.ddd.post.domain;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
public class PostCountInvoker {
    private CountPostCommand countPostCommand;

    public Integer executeCommand() {
        if(countPostCommand != null) {
            return countPostCommand.execute();
        } else {
            return 0;
        }
    }
}
