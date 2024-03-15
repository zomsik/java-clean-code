package pollub.projekt.ddd.post.domain;

import java.util.List;

public interface IterableCollection<T> {
    Iterator<T> createIterator();
    List<T> getSource();
}
