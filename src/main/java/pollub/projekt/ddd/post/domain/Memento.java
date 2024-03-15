package pollub.projekt.ddd.post.domain;

import java.time.LocalDateTime;

public interface Memento<T> {
    T getState();
    LocalDateTime getDateCreated();
}
