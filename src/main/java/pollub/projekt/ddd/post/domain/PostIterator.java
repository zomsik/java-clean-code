package pollub.projekt.ddd.post.domain;

import pollub.projekt.ddd.common.application.post.PostDto;

import java.util.List;

public class PostIterator implements Iterator<PostDto> {
    private final IterableCollection<PostDto> collection;
    private final List<PostDto> postList;
    private int currentPosition;

    public PostIterator(IterableCollection<PostDto> collection) {
        this.collection = collection;
        currentPosition = 0;
        postList = this.collection.getSource();
    }

    @Override
    public PostDto getNext() {
        if(currentPosition >= postList.size()) {
            throw new RuntimeException("Brak kolejnych elementow");
        }
        PostDto post = postList.get(currentPosition);
        currentPosition++;
        return post;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < postList.size();
    }
}
