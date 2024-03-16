package pollub.projekt.ddd.common.patterns.visitor;

public interface VisitElement {

    int accept(AccountVisitor accountVisitor);
}
