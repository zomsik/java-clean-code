package pollub.projekt.ddd.post.domain;

public interface Iterator<T> {
    T getNext();
    boolean hasNext();
}
