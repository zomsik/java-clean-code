package pollub.projekt.ddd.common.application.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pollub.projekt.ddd.common.application.account.AccountDto;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostDto {

    private Integer id;
    private AccountDto account;
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createDate;
    private Integer likes;
    private Integer comments;
    private boolean liked;


}
