package pollub.projekt.ddd.post.domain;

import java.time.LocalDateTime;

public class PostMemento implements Memento<Post>{
    final private Post post;
    final private LocalDateTime dateCreated;

    public PostMemento(Post post) {
        this.post = post;
        dateCreated = LocalDateTime.now();
    }

    @Override
    public Post getState() {
        return post;
    }

    @Override
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
}
