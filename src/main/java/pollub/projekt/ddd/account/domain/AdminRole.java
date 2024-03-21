package pollub.projekt.ddd.account.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminRole implements RoleState{
    private static final Logger logger = LoggerFactory.getLogger(AdminRole.class);

    @Override
    public String getId() {
        return "3";
    }

    @Override
    public String getName() {
        return "ADMIN";
    }

    @Override
    public void logAction(String action) {
        logger.info("Admin action: {}", action);
    }
}
