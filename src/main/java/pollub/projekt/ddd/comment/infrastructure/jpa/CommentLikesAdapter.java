package pollub.projekt.ddd.comment.infrastructure.jpa;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pollub.projekt.ddd.comment.domain.CommentLike;
import pollub.projekt.ddd.comment.domain.CommentLikesRepository;

import java.util.List;

@Repository
@Primary
@AllArgsConstructor
@Slf4j
public class CommentLikesAdapter implements CommentLikesRepository {

    /* Tydzień 3, Wzorzec Composite

        Wzorzec Composite pozwala tutaj na traktowanie różnych repozytorii jpa w identyczny sposób. Jest to przydatne w tym miejscu,
        gdyż dane aplikacji zapisywane są w kilku bazach danych i składają się na większą całość to mamy bezpośredni dostęp przy przeszukiwaniu ich
        używając tego wzorca.

    Koniec, Tydzień 3, Wzorzec Composite */

    private final List<CommentLikesJpaRepository> jpaRepositories;
    
    @Override
    public CommentLike save(CommentLike commentLike) {
        return jpaRepositories.getLast().save(commentLike.translate()).translate();
    }

    @Override
    public Integer getLikesOfComment(Integer commentId) {
        for (CommentLikesJpaRepository repository : jpaRepositories) {
            try {
                return repository.getLikesOfComment(commentId);
            } catch (Exception e) {
                log.info("Comment with id: {} not found in repo {}", commentId, repository.toString());
            }
        }
        return 0;
    }

    @Override
    public void deleteByCommentIdAndAccountId(Integer commentId, Integer accountId) {
        for (CommentLikesJpaRepository repository : jpaRepositories) {
            try {
                repository.deleteByCommentIdAndAccountId(commentId, accountId);
            } catch (Exception e) {
                log.info("Comment with id: {} not exist in repo {}", commentId, repository.toString());
            }
        }
    }
}
