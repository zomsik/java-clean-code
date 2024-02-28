package pollub.projekt.ddd.common.application.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

public interface TimeProvider {

    LocalTime currentTime();
    LocalDate currentDate();
    LocalDateTime currentDateTime();
    YearMonth currentMonth();
}
