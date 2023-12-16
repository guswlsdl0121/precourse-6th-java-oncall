package oncall.domain;

import java.time.LocalDate;

public record EmergencyDuty(LocalDate date, String crewName, boolean isHoliday) {
}
