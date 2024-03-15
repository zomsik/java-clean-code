package pollub.projekt.ddd.common.patterns.mediator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LogLevel {
    INFO("Info", "\u001B[37m"), IMPORTANT("Important", "\u001B[32m"), URGENT("Urgent", "\u001B[36m");

    private final String name;
    private final String color;
}
