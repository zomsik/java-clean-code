package pollub.projekt.ddd.post.rest.dto;


import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LikeResponseDto implements Serializable {

    private boolean success;
    private Integer likes;
}
