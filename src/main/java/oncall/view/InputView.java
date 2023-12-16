package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.dto.EmergencyDutyDTO;
import oncall.dto.MonthDayDTO;
import oncall.enums.DutyType;
import oncall.util.StringUtil;
import oncall.util.parser.MonthDayParser;
import oncall.util.parser.Parser;

public class InputView {
    private InputView() {
    }

    //5,월
    public static MonthDayDTO readMonthDay() {
        String input = StringUtil.removeWhitespace(Console.readLine());
        return MonthDayParser.parser(input);
    }

    public static EmergencyDutyDTO readWeekDayNames() {
        String input = StringUtil.removeWhitespace(Console.readLine());
        String[] splitInput = StringUtil.splitByComma(input);
        List<String> names = List.of(splitInput);
        return new EmergencyDutyDTO(DutyType.WEEKDAY, names);
    }

    public static EmergencyDutyDTO readHoliDayNames() {
        String input = StringUtil.removeWhitespace(Console.readLine());
        String[] splitInput = StringUtil.splitByComma(input);
        List<String> names = List.of(splitInput);
        return new EmergencyDutyDTO(DutyType.HOLIDAY, names);
    }
}
