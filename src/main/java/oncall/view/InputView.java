package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.dto.MonthDayDTO;
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

    public static void readNames() {
        String input = StringUtil.removeWhitespace(Console.readLine());
        String[] splitInput = StringUtil.splitByComma(input);
        List<Integer> numbers = Parser.parseNumbers(splitInput);
    }
}
