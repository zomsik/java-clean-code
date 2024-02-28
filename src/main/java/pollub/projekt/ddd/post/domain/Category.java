package pollub.projekt.ddd.post.domain;

import lombok.*;
import pollub.projekt.ddd.post.infrastructure.jpa.CategoryEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private Integer id;
    private String name;

    public CategoryEntity translate() {
        return CategoryEntity.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

    public Category(Integer id) {
        this.id = id;
    }

}
