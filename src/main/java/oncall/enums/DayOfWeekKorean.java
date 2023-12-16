package oncall.enums;

import java.time.DayOfWeek;
import java.util.Arrays;
import oncall.message.ErrorMessage;

public enum DayOfWeekKorean {
    SUNDAY("일", DayOfWeek.SUNDAY),
    MONDAY("월", DayOfWeek.MONDAY),
    TUESDAY("화", DayOfWeek.TUESDAY),
    WEDNESDAY("수", DayOfWeek.WEDNESDAY),
    THURSDAY("목", DayOfWeek.THURSDAY),
    FRIDAY("금", DayOfWeek.FRIDAY),
    SATURDAY("토", DayOfWeek.SATURDAY);

    private final String koreanName;
    private final DayOfWeek dayOfWeek;

    DayOfWeekKorean(String koreanName, DayOfWeek dayOfWeek) {
        this.koreanName = koreanName;
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public static DayOfWeek valueOfKorean(String koreanName) {
        return Arrays.stream(DayOfWeekKorean.values())
                .filter(e -> e.koreanName.equals(koreanName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INPUT))
                .getDayOfWeek();
    }
}
