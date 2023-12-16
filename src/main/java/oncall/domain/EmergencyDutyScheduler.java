package oncall.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import oncall.dto.MonthDayDTO;
import oncall.vo.Day;
import oncall.vo.Days;

public class EmergencyDutyScheduler {

    private static final int[] DAYS_IN_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private static final List<MonthDay> holidays = new ArrayList<MonthDay>();

    private static final int DAYS_IN_WEEK = 7;
    private static final int MONTH_OFFSET = 1;

    static {
        holidays.add(MonthDay.of(1, 1));
        holidays.add(MonthDay.of(3, 1));   // 삼일절
        holidays.add(MonthDay.of(5, 5));   // 어린이날
        holidays.add(MonthDay.of(6, 6));   // 현충일
        holidays.add(MonthDay.of(8, 15));  // 광복절
        holidays.add(MonthDay.of(10, 3));  // 개천절
        holidays.add(MonthDay.of( 10, 9));  // 한글날
        holidays.add(MonthDay.of(12, 25)); // 성탄절
    }

    public static Days generateDutyDates(MonthDayDTO monthDayDTO) {
        int month = monthDayDTO.month();
        DayOfWeek startDay = monthDayDTO.day();

        int daysInMonth = DAYS_IN_MONTH[month - MONTH_OFFSET];
        List<Day> days = new ArrayList<>();

        int dayOfWeekIndex = startDay.getValue();
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(LocalDate.now().getYear(), month, day);
            MonthDay monthDay = MonthDay.of(month, day);

            boolean isHoliday = holidays.contains(monthDay) || isWeekend(DayOfWeek.of(dayOfWeekIndex));
            days.add(new Day(date, isHoliday));

            dayOfWeekIndex = dayOfWeekIndex % DAYS_IN_WEEK + MONTH_OFFSET;
        }

        return new Days(days);
    }

    private static boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public static boolean isPublicHoliday(LocalDate date) {
        MonthDay monthDay = MonthDay.from(date);
        return holidays.contains(monthDay);
    }
}
