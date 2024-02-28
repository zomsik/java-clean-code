package pollub.projekt.ddd.comment.rest.dto;


import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCommentRequestDto implements Serializable {

    private String text;
    private Integer postId;
}
