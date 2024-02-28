package pollub.projekt.ddd.comment.rest.dto;


import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCommentResponseDto implements Serializable {

    private boolean success;
    private String message;
}
