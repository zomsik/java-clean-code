package pollub.projekt.ddd.post.rest.dto;

import lombok.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatePostRequestDto implements Serializable {
    private Integer postId;
    private String text;
}
