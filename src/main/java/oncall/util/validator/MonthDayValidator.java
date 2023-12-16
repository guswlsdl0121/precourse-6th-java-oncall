package oncall.util.validator;

import java.time.DayOfWeek;
import oncall.dto.MonthDayDTO;

public class MonthDayValidator {
    private MonthDayValidator() {
    }

    public static void validate(MonthDayDTO input) {
        if (input == null) {
            throw new IllegalArgumentException("입력이 비어있습니다!");
        }

        int month = input.month();
        DayOfWeek dayOfWeek = input.day();

        validateMonth(month);
        validateDayOfWeek(dayOfWeek);
    }

    private static void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("월은 1부터 12 사이의 값이어야 합니다.");
        }
    }

    private static void validateDayOfWeek(DayOfWeek dayOfWeek) {
        if (dayOfWeek == null) {
            throw new IllegalArgumentException("유효한 요일이 아닙니다.");
        }
    }
}
