package pollub.projekt.ddd.common.patterns.mediator;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Logger {
    private static final String ANSI_RESET = "\u001B[0m";

    public void log(String message, LogLevel logLevel) {
        System.out.println(logLevel.getColor() + LocalDateTime.now() + ": " + message + ANSI_RESET);
    }
}
