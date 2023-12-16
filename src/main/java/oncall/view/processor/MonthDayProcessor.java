package oncall.view.processor;

import oncall.dto.MonthDayDTO;
import oncall.util.validator.MonthDayValidator;
import oncall.view.InputView;
import oncall.view.OutputView;

public class MonthDayProcessor {
    private MonthDayProcessor() {
    }

    public static MonthDayDTO ask() {
        OutputView.printRequestMonthDay();
        MonthDayDTO monthDayDTO = InputView.readMonthDay();
        MonthDayValidator.validate(monthDayDTO);
        return monthDayDTO;
    }

    public static void printError(IllegalArgumentException e) {
        OutputView.printError(e.getMessage());
    }
}
