package pollub.projekt.ddd.common.patterns.mediator;

public interface Mediator {
    void notify(Object sender, String message);
}
