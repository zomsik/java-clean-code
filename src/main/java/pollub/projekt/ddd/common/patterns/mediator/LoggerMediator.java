package pollub.projekt.ddd.common.patterns.mediator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoggerMediator implements Mediator {
    private final Logger logger;

    @Override
    public void notify(Object sender, String message) {
        String className = sender.getClass().getName();
        message = className + " " + message;

        if(className.contains("Controller")) {
            logger.log( message, LogLevel.IMPORTANT);
        } else if(className.contains("Service")) {
            logger.log(message, LogLevel.URGENT);
        } else {
            logger.log(message, LogLevel.INFO);
        }
    }
}
