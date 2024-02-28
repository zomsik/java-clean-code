package pollub.projekt.ddd.common.application.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pollub.projekt.ddd.common.application.account.AccountDto;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommentDto {


    private Integer id;
    private AccountDto account;
    private Integer postId;
    private String text;
    private LocalDateTime createDate;
    private Integer likes;
    private boolean liked;

}
