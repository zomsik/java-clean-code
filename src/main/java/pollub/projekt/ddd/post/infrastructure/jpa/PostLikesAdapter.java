package pollub.projekt.ddd.post.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pollub.projekt.ddd.post.domain.PostLike;
import pollub.projekt.ddd.post.domain.PostLikesRepository;

@Repository
@Primary
@AllArgsConstructor
public class PostLikesAdapter implements PostLikesRepository {

    private final PostLikesJpaRepository postLikesJpaRepository;


    @Override
    public PostLike save(PostLike postLike) {

    /* Tydzień 2, Wzorzec Prototype

        Wzorzec Prototype umożliwia tworzenie nowych obiektów poprzez klonowanie istniejących obiektów.
        Klasy implementują interfejs PrototypePattern, a następnie korzystają z metody clone(), aby utworzyć kopię obiektu.
        Interfejs PrototypePattern zawiera:  Object clone(); co umożliwia zastosowanie w każdej innej klasie (dzięki późniejszemu rzutowaniu)

    Koniec, Tydzień 2, Wzorzec Prototype */

        PostLike likeCopy = (PostLike) postLike.clone();
        PostLikesEntity savedLike = postLikesJpaRepository.save(likeCopy.translate());

        postLike.setId(savedLike.getId());
        return postLike;
    }

    @Override
    public Integer getLikesOfPost(Integer postId) {
        return postLikesJpaRepository.getLikesOfPost(postId);
    }

    @Override
    public void deleteByPostIdAndAccountId(Integer postId, Integer accountId) {
        postLikesJpaRepository.deleteByPostIdAndAccountId(postId, accountId);
    }
}
