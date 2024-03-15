package pollub.projekt.ddd.post.domain;

import pollub.projekt.ddd.common.application.post.PostDto;

import java.util.ArrayList;
import java.util.List;

public class PostCollection implements IterableCollection<PostDto> {
    private final List<PostDto> posts;

    public PostCollection() {
        this(new ArrayList<>());
    }

    public PostCollection(List<PostDto> posts) {
        this.posts = posts;
    }

    @Override
    public Iterator<PostDto> createIterator() {
        return new PostIterator(this);
    }

    @Override
    public List<PostDto> getSource() {
        return posts;
    }
}
