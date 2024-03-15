package pollub.projekt.ddd.post.rest.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestorePostRequestDto implements Serializable {
    private Integer postId;
    private LocalDateTime date;
}
