package oncall.view;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import oncall.domain.EmergencyDutyScheduler;
import oncall.vo.Day;

public class OutputViewFormatter {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M월 d일 E", Locale.KOREAN);

    public static String formatDay(Day day) {
        String formattedDate = day.date().format(dateFormatter);
        String holidaySuffix = EmergencyDutyScheduler.isPublicHoliday(day.date()) ? "(휴일)" : "";
        return formattedDate + " " + holidaySuffix;
    }
}
