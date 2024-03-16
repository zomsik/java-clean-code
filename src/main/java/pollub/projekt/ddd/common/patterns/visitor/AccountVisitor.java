package pollub.projekt.ddd.common.patterns.visitor;

import pollub.projekt.ddd.account.domain.CachedAccountRepository;
import pollub.projekt.ddd.account.infrastructure.jpa.AccountAdapter;

public interface AccountVisitor {

    int visit(CachedAccountRepository cachedAccountRepository);
    int visit(AccountAdapter accountAdapter);
}
