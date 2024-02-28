package pollub.projekt.ddd.common.application.time;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

@Component
@Primary
class SpecificTimeProvider implements TimeProvider {

    @Override
    public LocalTime currentTime() {
        return LocalTime.now();
    }

    @Override
    public LocalDate currentDate() {
        return LocalDate.now();
    }

    @Override
    public LocalDateTime currentDateTime() {
        return LocalDateTime.now();
    }

    @Override
    public YearMonth currentMonth() {
        return YearMonth.now();
    }
}
