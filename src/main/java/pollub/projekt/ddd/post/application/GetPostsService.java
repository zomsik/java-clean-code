package pollub.projekt.ddd.post.application;

import org.springframework.stereotype.Service;
import pollub.projekt.ddd.common.application.account.AccountFacade;
import pollub.projekt.ddd.common.application.post.PostDto;
import pollub.projekt.ddd.common.utils.JwtUtil;
import pollub.projekt.ddd.post.domain.*;
import pollub.projekt.ddd.post.domain.exception.PostErrorCodes;
import pollub.projekt.ddd.post.domain.exception.PostException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class GetPostsService {

    private final AccountFacade accountFacade;
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private final PostCountInvoker countInvoker;

    public GetPostsService(AccountFacade accountFacade, PostRepository postRepository, PostCountInvoker countInvoker) {
        this.accountFacade = accountFacade;
        this.postRepository = postRepository;
        this.countInvoker = countInvoker;
        this.jwtUtil = JwtUtil.getInstance();
    }

    public List<PostDto> getPosts(String category, Integer page, Integer postsPerPage, String jwt) {

        if (page == null || postsPerPage == null)
            throw new PostException(PostErrorCodes.NO_FILTER);

        PostCollection collection;

        if (category != null && !category.isEmpty()) {
            collection = new PostCollection(postRepository
                    .getByCategoryAndPageAndPostsOnPage(category, page, postsPerPage)
                    .stream()
                    .map(Post::translateToDto)
                    .toList());
        } else {
            collection = new PostCollection(postRepository
                    .getByPageAndPostsOnPage(page, postsPerPage)
                    .stream()
                    .map(Post::translateToDto)
                    .toList());
        }


        if (jwtUtil.valid(jwt)) {
            String user = jwtUtil.getUser(jwt);
            Integer accountId = accountFacade.getIdByLogin(user);



            /* Tydzień 5, Wzorzec Iterator

            Iterator pozwala sekwencyjnie przechodzić pzez całą kolekcję bez eksponowania jej struktury dzięki istnieniu metod takich jak:
            getNext, hasNext. W ten sposób bezproblemowo możemy przejść przez kolekcję postów, a następnie dla każdego ustawić
             czy jest już polubiony przez użytkownika, który te posty pobiera.

            Koniec, Tydzień 5, Wzorzec Iterator */

            Iterator<PostDto> iterator = collection.createIterator();
            while(iterator.hasNext()) {
                PostDto post = iterator.getNext();
                if (postRepository.isPostLikedByUser(post.getId(), accountId))
                    post.setLiked(true);
            }
        }

        return collection.getSource();
    }

    public PostDto getPostById(Integer postId, String jwt) {
        if (postId == null) {
            return null;
        }

        PostDto post = postRepository.getById(postId).map(Post::translateToDto).orElse(null);

        if (post != null) {
            if (jwtUtil.valid(jwt)) {
                String user = jwtUtil.getUser(jwt);
                Integer accountId = accountFacade.getIdByLogin(user);

                if (postRepository.isPostLikedByUser(post.getId(), accountId))
                    post.setLiked(true);
            }
        }

        return post;
    }

    public byte[] getImageByPostId(Integer postId) {
        if (postId == null || postId <= 0)
            throw new PostException(PostErrorCodes.WRONG_POST_ID);

        byte[] bytes = postRepository.getImageByPostId(postId);

        if (bytes == null) {
            throw new PostException(PostErrorCodes.IMAGE_NOT_FOUND);
        }

        return bytes;
    }



    /* Tydzień 5, Wzorzec Command

    Wzorzec Command pozwala na stworzenie z żądania swoistego obiektu. Pozwala to na parametryzowanie metod przy użyciu różnych żądań, kolejkowanie, itd.
    Każda komenda implementuje interfejs z metodą execute(), która uruchamia żądanie, odseparowując klientów od odbiorców operacji.

    Koniec, Tydzień 5, Wzorzec Command */
    public Integer countPosts(String category) {
        if (Objects.equals(category, CategoryEnum.NONE.name)) {
            countInvoker.setCountPostCommand(new CountAll(postRepository));
        } else if (Arrays.stream(CategoryEnum.values()).anyMatch(cat -> cat.name().equalsIgnoreCase(category)) ){
            countInvoker.setCountPostCommand(new CountByCategory(postRepository, category));
        } else {
            countInvoker.setCountPostCommand(new CountAll(postRepository));
        }
        return countInvoker.executeCommand();
    }
}
