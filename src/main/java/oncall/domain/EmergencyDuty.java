package oncall.domain;

import java.time.LocalDate;

public class EmergencyDuty {
    private LocalDate date;
    private String crewName;
    private boolean isHoliday;

    public EmergencyDuty(LocalDate date, String crewName, boolean isHoliday) {
        this.date = date;
        this.crewName = crewName;
        this.isHoliday = isHoliday;
    }
}
