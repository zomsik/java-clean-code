package pollub.projekt.ddd.post.rest.dto;


import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePostResponseDto implements Serializable {

    private boolean success;
    private String message;
}
