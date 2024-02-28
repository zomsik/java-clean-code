package pollub.projekt.ddd.post.rest.dto;


import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePostRequestDto implements Serializable {

    private String text;
    private byte[] image;
    private String selectedCategory;
}
