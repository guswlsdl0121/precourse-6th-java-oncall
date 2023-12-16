package oncall.util.parser;

import java.time.DayOfWeek;
import oncall.dto.MonthDayDTO;
import oncall.enums.DayOfWeekKorean;
import oncall.message.ErrorMessage;
import oncall.message.SpecialCharacter;

public class MonthDayParser {

    private static final int PARTS_COUNT = 2;
    private static final int MONTH_INDEX = 0;
    private static final int DAY_INDEX = 1;
    private static final String DELIMITER = SpecialCharacter.COMMA.getValue();

    public static MonthDayDTO parser(String input) {
        String[] parts = input.split(DELIMITER);

        if (parts.length != PARTS_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }

        int month;
        try {
            month = Parser.parseNumber(parts[MONTH_INDEX].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }

        DayOfWeek dayOfWeek = parseDayOfWeek(parts);

        return new MonthDayDTO(month, dayOfWeek);
    }

    private static DayOfWeek parseDayOfWeek(String[] parts) {
        try {
            return DayOfWeekKorean.valueOfKorean(parts[DAY_INDEX].trim());
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }
}
