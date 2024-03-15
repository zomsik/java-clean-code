package pollub.projekt.ddd.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostOriginator {
    private Post post;
    private final PostRepository postRepository;

    public Memento<Post> save() throws CloneNotSupportedException {
        return new PostMemento((Post) post.clone());
    }

    public void restore(Memento<Post> memento) {
        post.setText(memento.getState().getText());
        postRepository.save(post);
    }
}
