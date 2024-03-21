package pollub.projekt.ddd.account.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModeratorRole implements RoleState{
    private static final Logger logger = LoggerFactory.getLogger(ModeratorRole.class);

    @Override
    public String getId() {
        return "2";
    }

    @Override
    public String getName() {
        return "MODERATOR";
    }

    @Override
    public void logAction(String action) {
        logger.info("Moderator action: {}", action);
    }
}
