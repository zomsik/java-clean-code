package pollub.projekt.ddd.account.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRole implements RoleState{
    private static final Logger logger = LoggerFactory.getLogger(UserRole.class);

    @Override
    public String getId() {
        return "1";
    }

    @Override
    public String getName() {
        return "USER";
    }

    @Override
    public void logAction(String action) {
        logger.info("User action: {}", action);
    }
}
