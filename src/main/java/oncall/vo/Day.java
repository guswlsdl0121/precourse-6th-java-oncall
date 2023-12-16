package oncall.vo;

import java.time.LocalDate;

public record Day(LocalDate date, boolean isHoliday) {
}
